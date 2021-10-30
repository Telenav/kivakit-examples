open module kivakit.examples.microservice
{
    // KivaKit
    requires kivakit.microservice;

    // Wicket
    requires wicket.extensions;
    requires wicket.jquery.ui;
    requires wicket.jquery.ui.core;
    requires wicket.util;
    requires kivakit.protostuff.merged;
    requires gson;

    // Module exports
    exports com.telenav.kivakit.examples.microservice;
}
