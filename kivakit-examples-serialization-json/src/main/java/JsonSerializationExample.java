package com.telenav.kivakit.examples.application;

import com.telenav.kivakit.application.Application;
import com.telenav.kivakit.resource.project.ResourceProject;

/**
 *
 */
public class JsonSerializationExample extends Application
{
    public static void main(final String[] arguments)
    {
        new JsonSerializationExample().run(arguments);
    }

    private JsonSerializationExample()
    {
        super(ResourceProject.get());
    }

    @Override
    protected void onRun()
    {
    }
}
