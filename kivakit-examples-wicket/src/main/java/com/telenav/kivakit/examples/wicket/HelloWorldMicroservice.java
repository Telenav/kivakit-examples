package com.telenav.kivakit.examples.wicket;

import com.telenav.kivakit.core.version.Version;
import com.telenav.kivakit.microservice.Microservice;
import com.telenav.kivakit.microservice.MicroserviceMetadata;
import com.telenav.kivakit.web.jetty.JettyServer;
import com.telenav.kivakit.web.wicket.WicketJettyPlugin;

public class HelloWorldMicroservice extends Microservice
{
    public static void main(String[] arguments)
    {
        new HelloWorldMicroservice().run(arguments);
    }

    @Override
    public MicroserviceMetadata metadata()
    {
        return new MicroserviceMetadata()
            .withName("hello world")
            .withDescription("Example microservice with Apache Wicket web application")
            .withVersion(Version.version(1, 0));
    }

    @Override
    protected void onMountJettyPlugins(JettyServer server)
    {
        server.mount("/", new WicketJettyPlugin(HelloWorldWebApplication.class));
    }
}
