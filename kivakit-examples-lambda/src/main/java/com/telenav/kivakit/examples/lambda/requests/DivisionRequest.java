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

package com.telenav.kivakit.examples.lambda.requests;

import com.dyuproject.protostuff.Tag;
import com.google.gson.annotations.Expose;
import com.telenav.kivakit.coredata.validation.BaseValidator;
import com.telenav.kivakit.coredata.validation.ValidationType;
import com.telenav.kivakit.coredata.validation.Validator;
import com.telenav.kivakit.microservice.microservlet.BaseMicroservletRequest;
import com.telenav.kivakit.microservice.microservlet.BaseMicroservletResponse;
import com.telenav.kivakit.microservice.microservlet.MicroservletRequest;
import com.telenav.kivakit.microservice.microservlet.MicroservletResponse;
import com.telenav.kivakit.microservice.protocols.rest.openapi.OpenApiIncludeMember;
import com.telenav.kivakit.microservice.protocols.rest.openapi.OpenApiIncludeType;
import com.telenav.kivakit.microservice.protocols.rest.openapi.OpenApiRequestHandler;

/**
 * A {@link MicroservletRequest} that performs arithmetic division
 *
 * @author jonathanl (shibo)
 * @see MicroservletRequest
 * @see MicroservletResponse
 */
@OpenApiIncludeType(description = "Request for divisive action")
public class DivisionRequest extends BaseMicroservletRequest
{
    @OpenApiIncludeType(description = "Response to a divide request")
    public class DivisionResponse extends BaseMicroservletResponse
    {
        public DivisionResponse()
        {
            quotient = dividend / divisor;
        }

        @Override
        public String toString()
        {
            return Integer.toString(quotient);
        }

        @Override
        public Validator validator(ValidationType type)
        {
            return Validator.NULL;
        }

        @Tag(1)
        @Expose
        @OpenApiIncludeMember(description = "The result of dividing the dividend by the divisor",
                              example = "42")
        int quotient;
    }

    @Tag(1)
    @Expose
    @OpenApiIncludeMember(description = "The number to be divided",
                          example = "84")
    private int dividend;

    @Tag(2)
    @Expose
    @OpenApiIncludeMember(description = "The number to divide the dividend by (dividend / divisor)",
                          example = "2")
    private int divisor;

    public DivisionRequest(int dividend, int divisor)
    {
        this.dividend = dividend;
        this.divisor = divisor;
    }

    public DivisionRequest()
    {
    }

    @Override
    @OpenApiRequestHandler(summary = "Divides two numbers")
    public DivisionResponse onRespond()
    {
        return listenTo(new DivisionResponse());
    }

    @Override
    public Class<DivisionResponse> responseType()
    {
        return DivisionResponse.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Validator validator(ValidationType type)
    {
        return new BaseValidator()
        {
            @Override
            protected void onValidate()
            {
                problemIf(divisor == 0, "Cannot divide by zero");
            }
        };
    }
}
