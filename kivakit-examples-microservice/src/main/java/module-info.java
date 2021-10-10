open module kivakit.examples.microservice
{
    // KivaKit
    requires transitive kivakit.microservice;

    // Wicket
    requires wicket.extensions;
    requires wicket.jquery.ui;
    requires wicket.jquery.ui.core;
    requires wicket.util;

    // Java
    requires java.sql;

    // Module exports
    exports com.telenav.kivakit.examples.microservice;
}
