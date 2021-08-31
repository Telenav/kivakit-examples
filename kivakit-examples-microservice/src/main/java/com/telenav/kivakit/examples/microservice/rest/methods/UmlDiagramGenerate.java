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

package com.telenav.kivakit.examples.microservice.rest.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.telenav.kivakit.filesystem.Folder;
import com.telenav.kivakit.kernel.data.validation.BaseValidator;
import com.telenav.kivakit.kernel.data.validation.ValidationType;
import com.telenav.kivakit.kernel.data.validation.Validator;
import com.telenav.kivakit.kernel.language.reflection.property.KivaKitIncludeProperty;
import com.telenav.kivakit.kernel.language.strings.Strings;
import com.telenav.kivakit.kernel.language.vm.Processes;
import com.telenav.kivakit.kernel.messaging.messages.status.Problem;
import com.telenav.kivakit.microservice.methods.MicroservicePostMethod;
import com.telenav.kivakit.network.http.HttpNetworkLocation;
import com.telenav.kivakit.resource.resources.jar.launcher.JarLauncher;
import io.swagger.v3.oas.annotations.media.Schema;

import static com.telenav.kivakit.resource.resources.jar.launcher.JarLauncher.ProcessType.CHILD;
import static com.telenav.kivakit.resource.resources.jar.launcher.JarLauncher.RedirectTo.CONSOLE;

/**
 * A REST method that generates a UML diagram for a package on GitHub.
 *
 * <p>
 * When {@link #respond()} is called, the following happens, in the given order:
 * </p>
 *
 * <ol>
 *     <li>The method {@link #newResponse()} is called by KivaKit, and returns an instance of {@link Response}</li>
 *     <li>{@link #validator(ValidationType)} is called to create a {@link Validator} for the request</li>
 *     <li>KivaKit invokes the {@link Validator} with {@link Response} as the listener to capture any validation problems</li>
 *     <ol type="a">
 *         <li>If the request passed validation, {@link #onRespond(Response)} is invoked, the specified
 *         source code is accessed on GitHub, and if Lexakai successfully processes it, the resulting UML diagram is returned</li>
 *         <li>If the request did not pass validation, a {@link Problem} is returned with the response</li>
 *     </ol>
 * </ol>
 *
 * @author jonathanl (shibo)
 */
@Schema(description = "A request for a UML diagram of a GitHub package")
public class UmlDiagramGenerate extends MicroservicePostMethod<UmlDiagramGenerate.Response>
{
    /**
     * The UML diagram for a {@link UmlDiagramGenerate}.
     *
     * @author jonathanl (shibo)
     */
    @Schema(description = "The UML diagram response")
    public static class Response extends MicroserviceResponse
    {
        @JsonProperty
        @Schema(description = "The requested plant UML diagram")
        String diagram;

        void diagram(String diagram)
        {
            this.diagram = diagram;
        }
    }

    @KivaKitIncludeProperty
    @JsonProperty
    @Schema(description = "The GitHub folder to process")
    private Folder folder;

    /**
     * {@inheritDoc}
     */
    public void onRespond(final Response response)
    {
        // Materialize the source code from GitHub,
        var source = folder.materialize();

        // and run Lexakai (https://www.lexakai.org) on it.
        var lexakai = HttpNetworkLocation.parse(this,
                "https://repo1.maven.org/maven2/com/telenav/lexakai/lexakai/1.0.0/lexakai-1.0.0.jar");

        var process = listenTo(new JarLauncher()
                .processType(CHILD)
                .arguments("-deployment=local -console-output=true " + source))
                .addJarSource(lexakai)
                .redirectTo(CONSOLE)
                .run();

        // If Lexakai produced a diagram as output,
        final String diagram = Processes.captureOutput(process);
        if (!Strings.isEmpty(diagram))
        {
            // then return that in the response,
            response.diagram(diagram);
        }
        else
        {
            // otherwise return an error.
            response.problem("No output captured from Lexakai");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Validator validator(final ValidationType type)
    {
        return new BaseValidator()
        {
            @Override
            protected void onValidate()
            {
                problemIf(folder == null, "Must provide github package to process");
                problemIf(!"github".equals(folder.path().scheme()), "Path to source code must start with github:");
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Response newResponse()
    {
        return new Response();
    }
}
