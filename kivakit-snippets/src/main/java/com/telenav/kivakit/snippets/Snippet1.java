package com.telenav.kivakit.snippets;

import static com.telenav.kivakit.core.collections.list.StringList.stringList;

public class Snippet1 extends BaseSnippet
{
    public static void main(String[] args)
    {
        new Snippet1().run();
    }

    @Override
    protected Object onSnippet()
    {
        return stringList("zebra", "koala", "kitten", "bear")
            .map(String::toUpperCase)
            .sorted()
            .numbered()
            .prefixedWith("> ")
            .indented(4, "-")
            .titledBox("Snippet 1");
    }
}
