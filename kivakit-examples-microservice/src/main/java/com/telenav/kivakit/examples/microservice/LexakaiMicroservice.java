////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// © 2011-2021 Telenav, Inc.
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package com.telenav.kivakit.examples.microservice;

import com.telenav.kivakit.examples.microservice.rest.LexakaiRestApplication;
import com.telenav.kivakit.examples.microservice.webapp.LexakaiWebApplication;
import com.telenav.kivakit.kernel.language.values.version.Version;
import com.telenav.kivakit.microservice.Microservice;
import com.telenav.kivakit.microservice.MicroserviceMetadata;

/**
 * Microservice example, including Apache Wicket, REST and Swagger support.
 *
 * @author jonathanl (shibo)
 */
@SuppressWarnings("ClassEscapesDefinedScope")
public class LexakaiMicroservice extends Microservice
{
    public static void main(final String[] arguments)
    {
        new LexakaiMicroservice().run(arguments);
    }

    /**
     * @return Metadata describing this microservice
     */
    @Override
    public MicroserviceMetadata metadata()
    {
        return new MicroserviceMetadata()
                .withName("lexakai-microservice")
                .withDescription("Lexakai microservice example")
                .withVersion(Version.parse("1.0"));
    }

    /**
     * @return The REST application for this microservice
     */
    protected LexakaiRestApplication restApplication()
    {
        return new LexakaiRestApplication(this);
    }

    /**
     * @return The Apache Wicket web application for this microservice
     */
    @Override
    protected LexakaiWebApplication webApplication()
    {
        return new LexakaiWebApplication(this);
    }
}
