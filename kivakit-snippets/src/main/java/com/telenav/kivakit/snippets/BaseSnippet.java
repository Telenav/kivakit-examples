package com.telenav.kivakit.snippets;

import com.telenav.kivakit.component.BaseComponent;

import static com.telenav.kivakit.core.os.Console.console;

public abstract class BaseSnippet extends BaseComponent implements Runnable
{
    @Override
    public void run()
    {
        println(onSnippet());
    }

    protected abstract Object onSnippet();

    protected void println(Object object)
    {
        console().println(object.toString());
    }
}
