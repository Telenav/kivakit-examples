////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// © 2011-2021 Telenav, Inc.
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package com.telenav.kivakit.examples.microservice;

import com.telenav.kivakit.examples.microservice.rest.UmlRestApplication;
import com.telenav.kivakit.examples.microservice.webapp.UmlWebApplication;
import com.telenav.kivakit.microservice.Microservice;

/**
 * Microservice example, including Apache Wicket, REST and Swagger interfaces.
 *
 * @author jonathanl (shibo)
 */
public class UmlMicroservice extends Microservice
{
    public static void main(final String[] arguments)
    {
        new UmlMicroservice().run(arguments);
    }

    protected UmlMicroservice()
    {
        super(new UmlMicroserviceProject());
    }

    @Override
    public String description()
    {
        return "KivaKit UML microservice example";
    }

    protected UmlRestApplication restApplication()
    {
        return new UmlRestApplication();
    }

    @Override
    protected UmlWebApplication webApplication()
    {
        return new UmlWebApplication();
    }
}
