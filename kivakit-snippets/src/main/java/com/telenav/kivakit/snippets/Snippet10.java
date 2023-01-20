package com.telenav.kivakit.snippets;

import com.telenav.kivakit.core.string.FormatProperty;
import com.telenav.kivakit.core.string.ObjectFormatter;

@SuppressWarnings({"FieldCanBeLocal", "unused", "InnerClassMayBeStatic"})
public class Snippet10 extends BaseSnippet
{
    public static void main(String[] args)
    {
        new Snippet10().run();
    }

    public class Alien
    {
        @FormatProperty
        private final String name;

        @FormatProperty
        private final Galaxy galaxy;

        public Alien(String name, Galaxy galaxy)
        {
            this.name = name;
            this.galaxy = galaxy;
        }

        @Override
        public String toString()
        {
            return new ObjectFormatter(this).toString();
        }
    }

    public class Galaxy
    {
        @FormatProperty
        private final String name;

        public Galaxy(String name)
        {
            this.name = name;
        }
    }

    @Override
    protected Object onSnippet()
    {
        return new Alien("Fred", new Galaxy("Andromeda")).toString();
    }
}

