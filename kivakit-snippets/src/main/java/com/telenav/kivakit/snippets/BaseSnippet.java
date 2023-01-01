package com.telenav.kivakit.snippets;

import com.telenav.kivakit.component.BaseComponent;

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
        System.out.println(object.toString());
    }
}
