package com.telenav.kivakit.snippets;

import com.telenav.kivakit.conversion.core.time.kivakit.HumanizedKivaKitLocalDateTimeConverter;

import static com.telenav.kivakit.core.time.LocalTime.now;

public class Snippet14 extends BaseSnippet
{
    public static void main(String[] args)
    {
        new Snippet14().run();
    }

    @Override
    protected Object onSnippet()
    {
        var converter = new HumanizedKivaKitLocalDateTimeConverter(this);
        return converter.unconvert(now().asLocalTime())
            + ", "
            + converter.convert("Today 6.00pm");
    }
}
