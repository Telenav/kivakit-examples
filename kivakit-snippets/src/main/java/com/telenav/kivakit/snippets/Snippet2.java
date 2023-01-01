package com.telenav.kivakit.snippets;

import static com.telenav.kivakit.filesystem.File.file;

public class Snippet2 extends BaseSnippet
{
    public static void main(String[] args)
    {
        new Snippet2().run();
    }

    @Override
    protected Object onSnippet()
    {
        return file("pom.xml").sizeInBytes();
    }
}
