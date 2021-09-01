open module kivakit.examples.microservice
{
    // KivaKit
    requires kivakit.web.jetty;
    requires kivakit.web.wicket;
    requires kivakit.web.jersey;
    requires kivakit.web.swagger;

    // Wicket
    requires wicket.extensions;
    requires wicket.jquery.ui;
    requires wicket.jquery.ui.core;
    requires wicket.util;

    // Serialization
    requires org.danekja.jdk.serializable.functional;
    requires org.apache.httpcomponents.httpclient;

    // Jersey
    requires jersey.common;
    requires jakarta.inject;
    requires jersey.container.servlet;
    requires jersey.container.servlet.core;
    requires jersey.server;
    requires jersey.client;

    // GitHub
    requires org.eclipse.jgit;
    requires github.api;

    // Swagger annotations
    requires io.swagger.v3.oas.annotations;
    requires kivakit.application;
    requires kivakit.microservice;
    requires kivakit.serialization.json;

    // Module exports
    exports com.telenav.kivakit.examples.microservice;
}
