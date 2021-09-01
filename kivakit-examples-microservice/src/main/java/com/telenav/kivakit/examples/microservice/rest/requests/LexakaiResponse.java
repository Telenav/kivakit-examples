package com.telenav.kivakit.examples.microservice.rest.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.telenav.kivakit.microservice.rest.MicroserviceRestRequest;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * The response to a {@link LexakaiRequest}.
 *
 * @author jonathanl (shibo)
 */
@Schema(description = "The UML diagram response")
public class LexakaiResponse extends MicroserviceRestRequest.MicroserviceResponse
{
    @JsonProperty
    @Schema(description = "The identifier of the pull request that was created")
    long pullRequestIdentifier;

    public long pullRequestIdentifier()
    {
        return pullRequestIdentifier;
    }
}
