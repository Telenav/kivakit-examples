////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// © 2011-2021 Telenav, Inc.
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package com.telenav.kivakit.examples.microservice;

import com.telenav.kivakit.kernel.language.values.version.Version;
import com.telenav.kivakit.microservice.Microservice;
import com.telenav.kivakit.microservice.MicroserviceMetadata;
import com.telenav.kivakit.microservice.protocols.grpc.MicroserviceGrpcService;

/**
 * Microservice example, including REST and Swagger support.
 *
 * @author jonathanl (shibo)
 */
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

    @Override
    public MicroserviceGrpcService onNewGrpcService()
    {
        return new DivisionGrpcService(this);
    }

    /**
     * @return The REST application for this microservice
     */
    public DivisionRestService onNewRestService()
    {
        return new DivisionRestService(this);
    }
}
