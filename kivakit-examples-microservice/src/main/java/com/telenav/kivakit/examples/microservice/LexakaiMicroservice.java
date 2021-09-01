////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// © 2011-2021 Telenav, Inc.
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package com.telenav.kivakit.examples.microservice;

import com.telenav.kivakit.examples.microservice.rest.LexakaiRestApplication;
import com.telenav.kivakit.examples.microservice.webapp.LexakaiWebApplication;
import com.telenav.kivakit.microservice.Microservice;

/**
 * Microservice example, including Apache Wicket, REST and Swagger interfaces.
 *
 * @author jonathanl (shibo)
 */
public class LexakaiMicroservice extends Microservice
{
    public static void main(final String[] arguments)
    {
        new LexakaiMicroservice().run(arguments);
    }

    protected LexakaiMicroservice()
    {
        super(new LexakaiMicroserviceProject());
    }

    @Override
    public String description()
    {
        return "KivaKit UML microservice example";
    }

    protected LexakaiRestApplication restApplication()
    {
        return new LexakaiRestApplication();
    }

    @Override
    protected LexakaiWebApplication webApplication()
    {
        return new LexakaiWebApplication();
    }
}
