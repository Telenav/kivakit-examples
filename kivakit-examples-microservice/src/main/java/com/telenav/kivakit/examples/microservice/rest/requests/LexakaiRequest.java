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

package com.telenav.kivakit.examples.microservice.rest.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.telenav.kivakit.examples.microservice.lexakai.LexakaiProcessor;
import com.telenav.kivakit.kernel.data.validation.BaseValidator;
import com.telenav.kivakit.kernel.data.validation.ValidationType;
import com.telenav.kivakit.kernel.data.validation.Validator;
import com.telenav.kivakit.kernel.language.reflection.property.KivaKitIncludeProperty;
import com.telenav.kivakit.kernel.messaging.messages.status.Problem;
import com.telenav.kivakit.microservice.rest.methods.MicroservicePostRequest;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * A REST method that generates a UML diagram for a package on GitHub.
 *
 * <p>
 * When {@link #respond()} is called, the following happens, in the given order:
 * </p>
 *
 * <ol>
 *     <li>The method {@link #newResponse()} is called by KivaKit, and returns an instance of {@link LexakaiResponse}</li>
 *     <li>{@link #validator(ValidationType)} is called to create a {@link Validator} for the request</li>
 *     <li>KivaKit invokes the {@link Validator} with {@link LexakaiResponse} as the listener to capture any validation problems</li>
 *     <ol type="a">
 *         <li>If the request passed validation, {@link #onRespond(LexakaiResponse)} is invoked, the specified
 *         source code is accessed on GitHub, and if Lexakai successfully processes it, the resulting UML diagram is returned</li>
 *         <li>If the request did not pass validation, a {@link Problem} is returned with the response</li>
 *     </ol>
 * </ol>
 *
 * @author jonathanl (shibo)
 */
@Schema(description = "A request for a UML diagram of a GitHub package")
public class LexakaiRequest extends MicroservicePostRequest<LexakaiResponse>
{
    @KivaKitIncludeProperty
    @JsonProperty
    @Schema(description = "The owner of the GitHub repository to process")
    private String owner;

    @KivaKitIncludeProperty
    @JsonProperty
    @Schema(description = "The public GitHub repository to process")
    private String repository;

    @KivaKitIncludeProperty
    @JsonProperty
    @Schema(description = "The branch to process")
    private String branch;

    public String branch()
    {
        return branch;
    }

    public LexakaiRequest branch(final String branch)
    {
        this.branch = branch;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public void onRespond(final LexakaiResponse response)
    {
        final var request = response.listenTo(new LexakaiProcessor()).process(owner, repository, branch);
        response.pullRequestIdentifier = request.getId();
    }

    public LexakaiRequest owner(final String owner)
    {
        this.owner = owner;
        return this;
    }

    public String owner()
    {
        return owner;
    }

    public LexakaiRequest repository(final String repository)
    {
        this.repository = repository;
        return this;
    }

    public String repository()
    {
        return repository;
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
                problemIf(!isGitHubIdentifier(owner), "Missing or invalid owner: '$'", owner);
                problemIf(!isGitHubIdentifier(repository), "Missing or invalid repository: '$'", repository);
                problemIf(!isGitHubIdentifier(branch), "Missing or invalid branch: '$'", branch);
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected LexakaiResponse newResponse()
    {
        return new LexakaiResponse();
    }

    private boolean isGitHubIdentifier(String text)
    {
        return text.matches("[A-Za-z0-9-]+");
    }
}
