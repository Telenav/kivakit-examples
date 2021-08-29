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

package com.telenav.kivakit.examples.microservice.rest.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.telenav.kivakit.filesystem.Folder;
import com.telenav.kivakit.kernel.data.validation.BaseValidator;
import com.telenav.kivakit.kernel.data.validation.ValidationType;
import com.telenav.kivakit.kernel.data.validation.Validator;
import com.telenav.kivakit.kernel.language.reflection.property.KivaKitIncludeProperty;
import com.telenav.kivakit.kernel.language.strings.formatting.ObjectFormatter;
import com.telenav.kivakit.kernel.language.vm.Processes;
import com.telenav.kivakit.kernel.messaging.Listener;
import com.telenav.kivakit.microservice.rest.protocol.MicroserviceRequest;
import com.telenav.kivakit.network.http.HttpNetworkLocation;
import com.telenav.kivakit.resource.resources.jar.launcher.JarLauncher;
import io.swagger.v3.oas.annotations.media.Schema;

import static com.telenav.kivakit.kernel.language.strings.formatting.ObjectFormatter.Format.SINGLE_LINE;
import static com.telenav.kivakit.resource.resources.jar.launcher.JarLauncher.ProcessType.CHILD;
import static com.telenav.kivakit.resource.resources.jar.launcher.JarLauncher.RedirectTo.CONSOLE;

/**
 * Requests for a UML diagram for a package on GitHub
 *
 * @author jonathanl (shibo)
 */
@Schema(description = "A request for a UML diagram of a GitHub package")
public class UmlDiagramRequest extends MicroserviceRequest
{
    @KivaKitIncludeProperty
    @JsonProperty
    @Schema(description = "The GitHub folder to process")
    private Folder folder;

    @Override
    public String toString()
    {
        return new ObjectFormatter(this).toString(SINGLE_LINE);
    }

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
     * @return The UML diagram for the given folder
     */
    String diagram(Listener listener)
    {
        if (isValid(listener))
        {
            var lexakai = HttpNetworkLocation.parse(this,
                    "https://repo1.maven.org/maven2/com/telenav/lexakai/lexakai/1.0.0/lexakai-1.0.0.jar");

            var source = folder.materialize();

            var process = listenTo(new JarLauncher()
                    .processType(CHILD)
                    .arguments("-deployment=local -console-output=true " + source))
                    .addJarSource(lexakai)
                    .redirectTo(CONSOLE)
                    .run();

            return Processes.captureOutput(process);
        }

        return null;
    }
}
