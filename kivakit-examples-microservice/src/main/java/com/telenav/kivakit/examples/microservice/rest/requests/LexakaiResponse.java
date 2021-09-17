package com.telenav.kivakit.examples.microservice.rest.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * The response to a {@link LexakaiRequest}.
 *
 * @author jonathanl (shibo)
 */
@Schema(description = "The UML diagram response")
public class LexakaiResponse
{
    @JsonProperty
    @Schema(description = "The identifier of the pull request that was created")
    long pullRequestIdentifier;

    public long pullRequestIdentifier()
    {
        return pullRequestIdentifier;
    }
}
