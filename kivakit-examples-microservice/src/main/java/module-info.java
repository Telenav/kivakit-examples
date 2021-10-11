open module kivakit.examples.microservice
{
    // KivaKit
    requires transitive kivakit.application;
    requires transitive kivakit.microservice;
    requires transitive kivakit.web.swagger;

    // Wicket
    requires wicket.extensions;
    requires wicket.jquery.ui;
    requires wicket.jquery.ui.core;
    requires wicket.util;

    requires gson;

    // Java
    requires java.sql;

    // Module exports
    exports com.telenav.kivakit.examples.microservice;
}
