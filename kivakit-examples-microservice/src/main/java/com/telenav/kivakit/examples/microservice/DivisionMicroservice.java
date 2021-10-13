////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// © 2011-2021 Telenav, Inc.
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package com.telenav.kivakit.examples.microservice;

import com.telenav.kivakit.kernel.language.values.version.Version;
import com.telenav.kivakit.microservice.Microservice;
import com.telenav.kivakit.microservice.MicroserviceMetadata;

/**
 * Microservice example, including REST and Swagger support.
 *
 * @author jonathanl (shibo)
 */
@SuppressWarnings("ClassEscapesDefinedScope")
public class DivisionMicroservice extends Microservice
{
    public static void main(final String[] arguments)
    {
        new DivisionMicroservice().run(arguments);
    }

    /**
     * @return Metadata describing this microservice
     */
    @Override
    public MicroserviceMetadata metadata()
    {
        return new MicroserviceMetadata()
                .withName("divide-microservice")
                .withDescription("Example microservice performing arithmetic division")
                .withVersion(Version.parse("1.0"));
    }

    /**
     * @return The REST application for this microservice
     */
    public DivisionRestService restService()
    {
        return new DivisionRestService(this);
    }
}
