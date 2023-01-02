package com.telenav.kivakit.snippets;

import com.telenav.kivakit.core.language.trait.TryTrait;
import com.telenav.kivakit.resource.Resource;
import com.telenav.kivakit.resource.packages.PackageTrait;

import static com.telenav.kivakit.core.collections.list.StringList.stringList;
import static com.telenav.kivakit.core.string.AsciiArt.line;
import static com.telenav.kivakit.filesystem.File.file;
import static com.telenav.kivakit.network.core.Host.host;
import static com.telenav.kivakit.network.http.secure.SecureHttpNetworkLocation.secureHttpNetworkLocation;

public class Snippet8 extends BaseSnippet implements
    TryTrait,
    PackageTrait
{
    public static void main(String[] args)
    {
        new Snippet8().run();
    }

    @Override
    protected Object onSnippet()
    {
        var licensePath = host("raw.githubusercontent.com")
            .https()
            .path("/Telenav/kivakit/releases/LICENSE");

        var httpGet = secureHttpNetworkLocation(licensePath).get();

        var file = file("kivakit/LICENSE");

        var packageResource = packageResource("resources/LICENSE");

        return stringList
            (
                read(file),
                read(packageResource),
                read(httpGet)
            )
            .join("\n" + line() + "\n");
    }

    private String read(Resource resource)
    {
        return resource
            .reader()
            .readLines()
            .first(3)
            .numbered()
            .join("\n");
    }
}
