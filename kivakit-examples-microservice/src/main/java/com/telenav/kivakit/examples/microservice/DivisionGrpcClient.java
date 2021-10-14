package com.telenav.kivakit.examples.microservice;

import com.telenav.kivakit.application.Application;
import com.telenav.kivakit.examples.microservice.requests.DivisionRequest;
import com.telenav.kivakit.examples.microservice.requests.DivisionRequest.DivisionResponse;
import com.telenav.kivakit.kernel.language.strings.AsciiArt;
import com.telenav.kivakit.kernel.language.values.version.Version;
import com.telenav.kivakit.kernel.messaging.Message;
import com.telenav.kivakit.microservice.MicroserviceSettings;
import com.telenav.kivakit.microservice.protocols.grpc.MicroserviceGrpcClient;
import com.telenav.kivakit.network.core.Host;

/**
 * Client application that divides two numbers by using the {@link DivisionMicroservice}, running in another process.
 *
 * @author jonathanl (shibo)
 */
public class DivisionGrpcClient extends Application
{
    public static void main(String[] arguments)
    {
        new DivisionGrpcClient().run(arguments);
    }

    @Override
    protected void onRun()
    {
        // Get the port and version of the microservice
        final var port = Host.local().http(requireSettings(MicroserviceSettings.class).grpcPort());

        // create a client to talk to the microservice REST API,
        var client = listenTo(new MicroserviceGrpcClient(port, Version.parse("1.0")));

        // then issue a divide request and read the response,
        var response = client.request("divide", new DivisionRequest(9, 3), DivisionResponse.class);

        // then show the response
        Message.println(AsciiArt.box("response => $", response));

        var future = client.requestFuture("divide", new DivisionRequest(10, 5), DivisionResponse.class);
        Message.println(AsciiArt.box("future response => $", future.get()));
    }
}
