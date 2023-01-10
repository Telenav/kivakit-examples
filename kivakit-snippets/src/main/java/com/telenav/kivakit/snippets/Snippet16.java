package com.telenav.kivakit.snippets;

import com.telenav.kivakit.application.Application;
import com.telenav.kivakit.core.object.Lazy;
import com.telenav.kivakit.core.os.ConsoleTrait;
import com.telenav.kivakit.serialization.gson.factory.KivaKitCoreGsonFactory;

import static com.telenav.kivakit.core.collections.list.StringList.split;
import static com.telenav.kivakit.core.object.Lazy.lazy;
import static com.telenav.kivakit.network.http.secure.SecureHttpNetworkLocation.secureHttpNetworkLocation;

public class Snippet16 extends Application implements ConsoleTrait
{
    public static void main(String[] arguments)
    {
        new Snippet16().run(arguments);
    }

    @Override
    protected void onRun()
    {
        println(properties().asJson());
    }
}
