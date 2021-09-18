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
import com.telenav.kivakit.kernel.language.strings.Strings;
import com.telenav.kivakit.microservice.rest.microservlet.MicroservletRequest;
import com.telenav.kivakit.microservice.rest.microservlet.MicroservletResponse;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * A {@link MicroservletRequest} handler that generates a UML diagram for a package on GitHub.
 *
 * @author jonathanl (shibo)
 * @see MicroservletRequest
 */
@Schema(description = "A request for a UML diagram of a GitHub package")
public class CreateLexakaiPullRequest implements MicroservletRequest
{
    /**
     * Response object that holds the pull request identifier created by processing this request
     *
     * @author jonathanl (shibo)
     */
    @Schema(description = "The UML diagram response")
    public static class LexakaiResponse implements MicroservletResponse
    {
        @JsonProperty
        @Schema(description = "The identifier of the pull request that was created")
        long pullRequestIdentifier;

        public long pullRequestIdentifier()
        {
            return pullRequestIdentifier;
        }
    }

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

    @Override
    public LexakaiResponse respond()
    {
        // Create our response object from the nested class,
        var response = new LexakaiResponse();

        // process the requested GitHub project branch,
        final var pullRequest = response
                .listenTo(new LexakaiProcessor())
                .process(owner, repository, branch);

        // and return the pull request identifier
        response.pullRequestIdentifier = pullRequest.getId();
        return response;
    }

    @Override
    public Class<MicroservletResponse> responseType()
    {
        return MicroservletResponse.class;
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

    private boolean isGitHubIdentifier(String text)
    {
        return !Strings.isEmpty(text) && text.matches("[A-Za-z0-9-]+");
    }
}
