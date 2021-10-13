package com.telenav.kivakit.examples.microservice;

import com.telenav.kivakit.application.Application;
import com.telenav.kivakit.examples.microservice.requests.DivideRequest;
import com.telenav.kivakit.kernel.language.strings.AsciiArt;
import com.telenav.kivakit.kernel.language.values.version.Version;
import com.telenav.kivakit.kernel.messaging.Message;
import com.telenav.kivakit.microservice.MicroserviceSettings;
import com.telenav.kivakit.microservice.protocols.rest.MicroserviceRestClient;
import com.telenav.kivakit.microservice.protocols.rest.gson.MicroserviceGsonFactory;
import com.telenav.kivakit.network.core.Host;

/**
 * Client application that divides two numbers by using the {@link DivisionMicroservice}, running in another process.
 *
 * @author jonathanl (shibo)
 */
public class DivisionRestClient extends Application
{
    public static void main(String[] arguments)
    {
        new DivisionRestClient().run(arguments);
    }

    @Override
    protected void onRun()
    {
        // Get the port and version of the microservice
        final var port = Host.local().http(requireSettings(MicroserviceSettings.class).port());
        final var version = Version.parse("1.0");

        // create a client to talk to the microservice REST API,
        var client = listenTo(new MicroserviceRestClient(
                new MicroserviceGsonFactory(), port, version));

        // then issue a divide request and read the response,
        var response = client.post("divide",
                DivideRequest.DivideResponse.class, new DivideRequest(9, 3));

        // then show the response
        Message.println(AsciiArt.box("response => $", response));
    }
}
