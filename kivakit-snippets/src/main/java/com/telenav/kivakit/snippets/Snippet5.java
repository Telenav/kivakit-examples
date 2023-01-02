package com.telenav.kivakit.snippets;

import static com.telenav.kivakit.core.version.Version.version;

public class Snippet5 extends BaseSnippet
{
    public static void main(String[] args)
    {
        new Snippet5().run();
    }

    @Override
    protected Object onSnippet()
    {
        var a = version("1.5.0");
        var b = version("2.3.2");

        return a.isOlderThan(b)
            ? a + " < " + b
            : b + " < " + a;
    }
}
