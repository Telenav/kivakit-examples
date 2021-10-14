package com.telenav.kivakit.examples.microservice;

import com.telenav.kivakit.application.Application;
import com.telenav.kivakit.examples.microservice.requests.DivideRequest;
import com.telenav.kivakit.examples.microservice.requests.DivideRequest.DivideResponse;
import com.telenav.kivakit.kernel.language.strings.AsciiArt;
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
        var client = listenTo(new MicroserviceGrpcClient(port));

        // then issue a divide request and read the response,
        var response = client.request("/api/1.0/divide", new DivideRequest(9, 3), DivideResponse.class);

        // then show the response
        Message.println(AsciiArt.box("response => $", response));

        var future = client.requestFuture("/api/1.0/divide", new DivideRequest(10, 5), DivideResponse.class);
        Message.println(AsciiArt.box("future response => $", future.get()));
    }
}
