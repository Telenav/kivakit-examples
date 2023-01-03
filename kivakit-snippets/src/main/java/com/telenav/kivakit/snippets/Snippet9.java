package com.telenav.kivakit.snippets;

import java.time.ZoneId;

import static com.telenav.kivakit.core.string.Formatter.format;
import static com.telenav.kivakit.core.time.Time.now;
import static com.telenav.kivakit.core.time.TimeZones.utc;

public class Snippet9 extends BaseSnippet
{
    public static void main(String[] args)
    {
        new Snippet9().run();
    }

    @Override
    protected Object onSnippet()
    {
        var now = now();
        var nowUtc = now.inTimeZone(utc());
        var nowLocal = now.inTimeZone(ZoneId.systemDefault());
        return format("utc => $\nlocal => $", nowUtc, nowLocal);
    }
}

