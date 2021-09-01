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

package com.telenav.kivakit.examples.microservice.rest;

import com.telenav.kivakit.examples.microservice.rest.requests.UmlDiagramRequest;
import com.telenav.kivakit.microservice.rest.MicroserviceRestRequest;
import com.telenav.kivakit.web.jersey.BaseRestResource;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * The REST resource for this microservice. Provides OpenAPI metadata to allow for automatic documentation via Swagger.
 *
 * @author jonathanl (shibo)
 */
@OpenAPIDefinition(info = @Info(
        title = "KivaKit Microservice Example",
        description = "REST API for KivaKit microservices example",
        version = "1.0.0",
        contact = @Contact(name = "Jonathan Locke", email = "jonathanl@telenav.com"),
        license = @License(name = "Apache License 2.0")
))
@Path("api/1.0.0")
public class UmlOpenApiRestResource extends BaseRestResource
{
    @POST
    @Path("uml")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(operationId = "uml",
               method = "POST",
               description = "Produces a UML diagram for the given GitHub folder")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                         description = "Success",
                         content = @Content(
                                 schema = @Schema(implementation = UmlDiagramRequest.Response.class))
            ),
            @ApiResponse(responseCode = "500",
                         description = "Failure",
                         content = @Content(
                                 schema = @Schema(implementation = UmlDiagramRequest.Response.class))
            )
    })
    public MicroserviceRestRequest.MicroserviceResponse onUml(
            @Parameter(name = "request",
                       description = "The UML request",
                       required = true,
                       schema = @Schema(implementation = UmlDiagramRequest.class))

            final UmlDiagramRequest request,
            @Context final HttpServletResponse servletResponse
    )
    {
        return request.respond();
    }
}