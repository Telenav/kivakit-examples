package com.telenav.kivakit.snippets;

import com.telenav.kivakit.core.language.trait.TryTrait;

import static com.telenav.kivakit.network.https.HttpsNetworkLocation.secureHttpNetworkLocation;

public class Snippet7 extends BaseSnippet implements TryTrait
{
    public static void main(String[] args)
    {
        new Snippet7().run();
    }

    @Override
    protected Object onSnippet()
    {
        return secureHttpNetworkLocation("https://raw.githubusercontent.com/Telenav/kivakit/releases/kivakit-network/http/src/main/java/com/telenav/kivakit/network/http/HttpMethod.java")
            .get()
            .readText();
    }
}
