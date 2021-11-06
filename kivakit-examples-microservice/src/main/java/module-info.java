open module kivakit.examples.microservice
{
    // KivaKit
    requires kivakit.microservice;

    // Wicket
    requires kivakit.protostuff.merged;
    requires gson;

    // Module exports
    exports com.telenav.kivakit.examples.microservice;
}
