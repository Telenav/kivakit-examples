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

package com.telenav.kivakit.examples.microservice.webapp.pages.home;

import com.telenav.kivakit.examples.microservice.rest.requests.LexakaiRequest;
import com.telenav.kivakit.examples.microservice.webapp.LexakaiWebPage;
import com.telenav.kivakit.web.wicket.components.feedback.FeedbackPanel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;

/**
 * Home page to allow user to enter a public GitHub repository and branch.
 *
 * @author jonathanl (shibo)
 */
public class HomePage extends LexakaiWebPage
{
    private final TextField<Object> owner = new TextField<>("owner");

    private final TextField<Object> repository = new TextField<>("repository");

    private final TextField<Object> branch = new TextField<>("branch");

    private final FeedbackPanel feedback = new FeedbackPanel("feedback");

    public HomePage()
    {
        var form = new Form<String>("form")
        {
            @Override
            protected void onSubmit()
            {
                // Execute a request (without REST) to process the given branch of the given repository.
                // Any problems and warnings will be shown in the feedback panel.
                new LexakaiRequest()
                        .owner(owner.getValue())
                        .repository(repository.getValue())
                        .branch(branch.getValue())
                        .respond(feedback);
            }
        };

        add(feedback);

        form.add(owner);
        form.add(repository);
        form.add(branch);

        add(form);
    }
}
