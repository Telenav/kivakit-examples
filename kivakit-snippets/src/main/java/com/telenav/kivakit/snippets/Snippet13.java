package com.telenav.kivakit.snippets;

import com.telenav.kivakit.core.registry.RegistryTrait;
import com.telenav.kivakit.core.string.FormatProperty;
import com.telenav.kivakit.core.string.ObjectFormatter;
import com.telenav.kivakit.core.value.count.Count;
import com.telenav.kivakit.network.core.Host;

import static com.telenav.kivakit.core.value.count.Count._3;
import static com.telenav.kivakit.network.core.Host.host;

public class Snippet13 extends BaseSnippet implements RegistryTrait
{
    public static void main(String[] args)
    {
        new Snippet13().run();
    }

    record Settings(@FormatProperty Host host,
                    @FormatProperty Count iterations)
    {
        @Override
        public String toString()
        {
            return new ObjectFormatter(this).toString();
        }
    }

    public Snippet13()
    {
        register(new Settings(host("google.com", "Google"), _3));
    }

    @Override
    protected Object onSnippet()
    {
        return require(Settings.class);
    }
}
