open module kivakit.examples.microservice
{
    // KivaKit
    requires kivakit.microservice;
    requires gson;
    requires kivakit.web.wicket;
    requires org.apache.wicket.core;

    // Module exports
    exports com.telenav.kivakit.examples.microservice;
    exports com.telenav.kivakit.examples.microservice.web;
}
