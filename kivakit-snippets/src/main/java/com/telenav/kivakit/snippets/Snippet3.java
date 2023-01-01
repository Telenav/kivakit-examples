package com.telenav.kivakit.snippets;

import static com.telenav.kivakit.core.collections.list.ObjectList.list;
import static com.telenav.kivakit.core.collections.list.StringList.stringList;
import static com.telenav.kivakit.filesystem.File.file;
import static com.telenav.kivakit.filesystem.Folders.kivakitTemporaryFolder;
import static com.telenav.kivakit.resource.FileName.fileName;
import static com.telenav.kivakit.resource.compression.archive.ZipArchive.AccessMode.READ;
import static com.telenav.kivakit.resource.compression.archive.ZipArchive.AccessMode.WRITE;
import static com.telenav.kivakit.resource.compression.archive.ZipArchive.zipArchive;

public class Snippet3 extends BaseSnippet
{
    public static void main(String[] args)
    {
        new Snippet3().run();
    }

    @Override
    protected Object onSnippet()
    {
        var file = kivakitTemporaryFolder().file("output.zip");
        var result = stringList();

        try (var zip = zipArchive(this, file, WRITE))
        {
            zip.add(list(
                file("kivakit/pom.xml"),
                file("kivakit/README.md"),
                file("kivakit/.gitignore")
            ));
        }

        try (var zip = zipArchive(this, file, READ))
        {
            for (var entry : zip)
            {
                result.add(entry.toString());

                if (entry.fileName().equals(fileName(".gitignore")))
                {
                    result.add(entry.readText().trim());
                }
            }
        }

        return result.join("\n");
    }
}
