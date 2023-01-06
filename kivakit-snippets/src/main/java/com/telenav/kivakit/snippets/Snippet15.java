package com.telenav.kivakit.snippets;

import com.telenav.kivakit.core.object.Lazy;

import static com.telenav.kivakit.core.collections.list.StringList.split;
import static com.telenav.kivakit.core.object.Lazy.lazy;
import static com.telenav.kivakit.network.http.secure.SecureHttpNetworkLocation.secureHttpNetworkLocation;

public class Snippet15 extends BaseSnippet
{
    public static void main(String[] args)
    {
        new Snippet15().run();
    }

    private final Lazy<String> home = lazy(() ->
        secureHttpNetworkLocation("https://www.kivakit.org")
            .get()
            .readText());

    @Override
    protected Object onSnippet()
    {
        return split(home.get(), "\n")
            .numbered()
            .first(5)
            .titledBox("KivaKit Home");
    }
}
