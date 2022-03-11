package com.telenav.kivakit.examples.microservice;

import com.telenav.kivakit.application.Application;
import com.telenav.kivakit.core.os.Console;
import com.telenav.kivakit.core.string.AsciiArt;
import com.telenav.kivakit.core.version.Version;
import com.telenav.kivakit.examples.microservice.requests.DivisionRequest;
import com.telenav.kivakit.microservice.MicroserviceSettings;
import com.telenav.kivakit.microservice.protocols.rest.MicroserviceRestClient;
import com.telenav.kivakit.network.core.Host;
import com.telenav.kivakit.serialization.gson.GsonObjectSerializer;
import com.telenav.kivakit.serialization.gson.factory.CoreGsonFactory;

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
        var port = Host.local().http(requireSettings(MicroserviceSettings.class).port());
        var version = Version.version("1.0");

        // create a client to talk to the microservice REST API,
        register(new CoreGsonFactory(this));
        var client = listenTo(new MicroserviceRestClient(new GsonObjectSerializer(), port, version));

        // then issue a divide request and read the response,
        var response = client.post("divide",
                DivisionRequest.DivisionResponse.class, new DivisionRequest(9, 3));

        // then show the response
        Console.println(AsciiArt.box("response => $", response));
    }
}
