package com.telenav.kivakit.examples.microservice;

import com.telenav.kivakit.examples.microservice.web.DivisionHomePage;
import com.telenav.kivakit.microservice.Microservice;
import com.telenav.kivakit.microservice.web.MicroserviceWebApplication;
import org.apache.wicket.Page;

public class DivisionWebApplication extends MicroserviceWebApplication
{
    /**
     * The microservice parameter to this class is ignored for now
     */
    public DivisionWebApplication(Microservice<?> microservice)
    {
        super(microservice);
    }

    @Override
    public Class<? extends Page> getHomePage()
    {
        return DivisionHomePage.class;
    }
}
