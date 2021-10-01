////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// © 2011-2021 Telenav, Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package com.telenav.kivakit.examples.microservice.webapp;

import com.telenav.kivakit.examples.microservice.LexakaiMicroservice;
import com.telenav.kivakit.examples.microservice.webapp.pages.home.HomePage;
import com.telenav.kivakit.microservice.web.MicroserviceWebApplication;
import com.telenav.lexakai.annotations.LexakaiJavadoc;
import org.apache.wicket.Page;

/**
 * Apache Wicket web application that shows information about registered services in a web browser.
 *
 * @author jonathanl (shibo)
 */
@LexakaiJavadoc(complete = true)
public class LexakaiWebApplication extends MicroserviceWebApplication
{
    public LexakaiWebApplication(LexakaiMicroservice microservice)
    {
        super(microservice);
    }

    @Override
    public Class<? extends Page> getHomePage()
    {
        return HomePage.class;
    }

    @Override
    public void init()
    {
        mountPage("/home", HomePage.class);
    }
}
