package com.telenav.kivakit.examples.microservice;

import com.telenav.kivakit.application.Application;
import com.telenav.kivakit.core.version.Version;
import com.telenav.kivakit.examples.microservice.requests.DivisionRequest;
import com.telenav.kivakit.examples.microservice.requests.DivisionRequest.DivisionResponse;
import com.telenav.kivakit.microservice.MicroserviceSettings;
import com.telenav.kivakit.microservice.protocols.rest.http.RestClient;
import com.telenav.kivakit.serialization.gson.GsonObjectSerializer;
import com.telenav.kivakit.serialization.gson.KivaKitCoreGsonFactory;

import static com.telenav.kivakit.core.os.Console.console;
import static com.telenav.kivakit.core.string.AsciiArt.textBox;
import static com.telenav.kivakit.network.core.LocalHost.localhost;

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
        var port = localhost().http(requireSettings(MicroserviceSettings.class).port());

        // create a client to talk to the microservice REST API,
        register(new KivaKitCoreGsonFactory());
        var client = listenTo(new RestClient(new GsonObjectSerializer(), port, Version.version("1.0")));

        // then issue a divide request and read the response,
        var response = client.post("divide",
            new DivisionRequest(9, 3), DivisionResponse.class);

        // then show the response
        console().println(textBox("response => $", response));
    }
}
