open module kivakit.examples.microservice
{
    // KivaKit
    requires kivakit.microservice;
    requires kivakit.web.jersey;
    requires kivakit.web.wicket;

    // Wicket
    requires wicket.extensions;
    requires wicket.jquery.ui;
    requires wicket.jquery.ui.core;
    requires wicket.util;

    // GitHub
    requires org.eclipse.jgit;
    requires github.api;

    // Swagger annotations
    requires io.swagger.v3.oas.annotations;

    // Module exports
    exports com.telenav.kivakit.examples.microservice;
}
