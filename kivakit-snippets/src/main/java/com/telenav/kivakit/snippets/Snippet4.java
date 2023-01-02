package com.telenav.kivakit.snippets;

import static com.telenav.kivakit.core.time.Duration.seconds;
import static com.telenav.kivakit.core.time.Time.now;

public class Snippet4 extends BaseSnippet
{
    public static void main(String[] args)
    {
        new Snippet4().run();
    }

    @Override
    protected Object onSnippet()
    {
        var start = now();
        seconds(0.5).sleep();
        return start.elapsedSince();
    }
}
