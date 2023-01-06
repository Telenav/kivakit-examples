package com.telenav.kivakit.snippets;

import com.telenav.kivakit.mixins.AttributesMixin;

public class Snippet12 extends BaseSnippet
{
    public static void main(String[] args)
    {
        new Snippet12().run();
    }

    record Person(String name) implements AttributesMixin<String, String>
    {
    }

    @Override
    protected Object onSnippet()
    {
        var shibo = new Person("shibo");
        shibo.attribute("favorite-color", "orange");
        return shibo.attribute("favorite-color");
    }
}
