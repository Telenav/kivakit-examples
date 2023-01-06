package com.telenav.kivakit.examples.wicket;

import org.apache.wicket.protocol.http.WebApplication;

@SuppressWarnings("rawtypes")
public class HelloWorldWebApplication extends WebApplication
{
    public HelloWorldWebApplication()
    {
    }

    @Override
    public Class getHomePage()
    {
        return HelloWorld.class;
    }
}
