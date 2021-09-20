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
import com.telenav.kivakit.examples.microservice.LexakaiProcessor;
import com.telenav.kivakit.kernel.data.validation.BaseValidator;
import com.telenav.kivakit.kernel.data.validation.ValidationType;
import com.telenav.kivakit.kernel.data.validation.Validator;
import com.telenav.kivakit.kernel.language.strings.Strings;
import com.telenav.kivakit.microservice.rest.microservlet.model.MicroservletResponse;
import com.telenav.kivakit.microservice.rest.microservlet.model.requests.MicroservletGetRequest;
import com.telenav.kivakit.microservice.rest.microservlet.model.requests.MicroservletPostRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * A {@link MicroservletGetRequest} request that generates a UML diagram for a package on GitHub.
 *
 * @author jonathanl (shibo)
 * @see MicroservletGetRequest
 * @see MicroservletResponse
 */
public class CreateLexakaiPullRequest extends MicroservletPostRequest
{
    /**
     * Response object that holds the pull request identifier created by processing this request
     *
     * @author jonathanl (shibo)
     */
    @Schema(description = "The UML diagram response")
    public static class Response extends MicroservletResponse
    {
        @JsonProperty
        @Schema(description = "The identifier of the pull request that was created")
        long pullRequestIdentifier;

        public long pullRequestIdentifier()
        {
            return pullRequestIdentifier;
        }
    }

    @JsonProperty
    @Schema(description = "The owner of the GitHub repository to process")
    private String owner;

    @JsonProperty
    @Schema(description = "The public GitHub repository to process")
    private String repository;

    @JsonProperty
    @Schema(description = "The branch to process")
    private String branch;

    @Override
    @Operation(summary = "A request for a UML diagram of a GitHub package")
    public Response onPost()
    {
        // Create our response object from the nested class,
        final var response = listenTo(new Response());

        // process the requested GitHub project branch,
        final var pullRequest = response
                .listenTo(new LexakaiProcessor())
                .process(owner, repository, branch);

        // and return the pull request identifier
        response.pullRequestIdentifier = pullRequest.getId();
        return response;
    }

    @Override
    public Class<Response> responseType()
    {
        return Response.class;
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

    private boolean isGitHubIdentifier(final String text)
    {
        return !Strings.isEmpty(text) && text.matches("[A-Za-z0-9-]+");
    }
}
