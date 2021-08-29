package com.telenav.kivakit.examples.microservice;

import com.telenav.kivakit.kernel.data.conversion.string.primitive.IntegerConverter;
import com.telenav.kivakit.kernel.language.reflection.populator.KivaKitPropertyConverter;

/**
 * Settings for {@link UmlMicroservice}
 *
 * @author jonathanl (shibo)
 */
@SuppressWarnings("unused")
public class UmlMicroserviceSettings
{
    @KivaKitPropertyConverter(IntegerConverter.class)
    private int port;

    int port()
    {
        return port;
    }
}
