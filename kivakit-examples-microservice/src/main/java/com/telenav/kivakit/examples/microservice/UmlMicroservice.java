////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// © 2011-2021 Telenav, Inc.
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package com.telenav.kivakit.examples.microservice;

import com.telenav.kivakit.examples.microservice.rest.UmlRestApplication;
import com.telenav.kivakit.examples.microservice.webapp.UmlWebApplication;
import com.telenav.kivakit.microservice.Microservice;
import com.telenav.kivakit.microservice.rest.MicroserviceRestApplication;
import com.telenav.kivakit.microservice.web.MicroserviceWicketWebApplication;
import com.telenav.kivakit.web.jersey.JettyJersey;
import com.telenav.kivakit.web.jetty.JettyServer;
import com.telenav.kivakit.web.jetty.resources.JettyStaticResources;
import com.telenav.kivakit.web.swagger.JettySwaggerIndex;
import com.telenav.kivakit.web.swagger.JettySwaggerOpenApi;
import com.telenav.kivakit.web.swagger.JettySwaggerStaticResources;
import com.telenav.kivakit.web.swagger.JettySwaggerWebJar;
import com.telenav.kivakit.web.wicket.JettyWicket;

/**
 * Microservice example, including Apache Wicket, REST and Swagger interfaces.
 *
 * @author jonathanl (shibo)
 */
public class UmlMicroservice extends Microservice
{
    public static void main(final String[] arguments)
    {
        new UmlMicroservice().run(arguments);
    }

    protected UmlMicroservice()
    {
        super(new UmlMicroserviceProject());
    }

    @Override
    public String description()
    {
        return "KivaKit UML microservice example";
    }

    public MicroserviceRestApplication restApplication()
    {
        return new UmlRestApplication();
    }

    @Override
    public MicroserviceWicketWebApplication webApplication()
    {
        return new UmlWebApplication();
    }

    @Override
    protected void onRun()
    {
        // Show command line arguments,
        showCommandLine();

        // get the port to run on,
        final var port = settings().port();

        // create the Jersey REST application,
        final var application = listenTo(new UmlRestApplication());

        // and start up Jetty with Swagger, Jersey and Wicket.
        listenTo(new JettyServer())
                .port(port)
                .add("/*", new JettyWicket(UmlWebApplication.class))
                .add("/open-api/*", new JettySwaggerOpenApi(application))
                .add("/docs/*", new JettySwaggerIndex(port))
                .add("/webapp/*", new JettySwaggerStaticResources())
                .add("/webjar/*", new JettySwaggerWebJar(application))
                .add("/ui/*", new JettyStaticResources(getClass(), "ui"))
                .add("/*", new JettyJersey(application))
                .start();
    }

    private UmlMicroserviceSettings settings()
    {
        return require(UmlMicroserviceSettings.class);
    }
}
