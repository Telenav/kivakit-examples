////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// © 2011-2021 Telenav, Inc.
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package com.telenav.kivakit.examples.microservice;

import com.telenav.kivakit.kernel.language.values.version.Version;
import com.telenav.kivakit.microservice.Microservice;
import com.telenav.kivakit.microservice.MicroserviceMetadata;
import com.telenav.kivakit.microservice.web.MicroserviceWebApplication;

/**
 * Microservice example, including REST and Swagger support.
 *
 * @author jonathanl (shibo)
 */
public class DivisionMicroservice extends Microservice<Void>
{
    public static void main(String[] arguments)
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
                .withName("division-microservice")
                .withDescription("Example microservice performing arithmetic division")
                .withVersion(Version.of(1, 0));
    }

    /**
     * @return The REST application for this microservice
     */
    @Override
    public DivisionRestService onNewRestService()
    {
        return new DivisionRestService(this);
    }

    @Override
    public MicroserviceWebApplication onNewWebApplication()
    {
        return new DivisionWebApplication(this);
    }
}
