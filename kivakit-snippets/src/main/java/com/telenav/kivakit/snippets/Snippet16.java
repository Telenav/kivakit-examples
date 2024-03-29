package com.telenav.kivakit.snippets;

import com.telenav.kivakit.application.Application;
import com.telenav.kivakit.core.os.ConsoleTrait;

public class Snippet16 extends Application implements ConsoleTrait
{
    public static void main(String[] arguments)
    {
        new Snippet16().run(arguments);
    }

    @Override
    protected void onRun()
    {
        println(properties().toJson());
    }
}
