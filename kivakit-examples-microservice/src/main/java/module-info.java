open module kivakit.examples.microservice
{
    // KivaKit
    requires kivakit.microservice;

    // Wicket
    requires wicket.extensions;
    requires wicket.jquery.ui;
    requires wicket.jquery.ui.core;
    requires wicket.util;

    // GitHub
    requires org.eclipse.jgit;
    requires github.api;

    // Module exports
    exports com.telenav.kivakit.examples.microservice;
}
