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

package com.telenav.kivakit.examples.microservice.requests;

import com.google.gson.annotations.Expose;
import com.telenav.kivakit.kernel.data.validation.BaseValidator;
import com.telenav.kivakit.kernel.data.validation.ValidationType;
import com.telenav.kivakit.kernel.data.validation.Validator;
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
public class DivideRequest extends BaseMicroservletRequest
{
    @OpenApiIncludeType(description = "Response to a divide request")
    public class DivideResponse extends BaseMicroservletResponse
    {
        @Expose
        @OpenApiIncludeMember(description = "The result of dividing the dividend by the divisor",
                              example = "42")
        int quotient;

        public DivideResponse()
        {
            this.quotient = dividend / divisor;
        }

        public String toString()
        {
            return Integer.toString(quotient);
        }

        @Override
        public Validator validator(final ValidationType type)
        {
            return Validator.NULL;
        }
    }

    @Expose
    @OpenApiIncludeMember(description = "The number to be divided",
                          example = "84")
    private int dividend;

    @Expose
    @OpenApiIncludeMember(description = "The number to divide the dividend by (dividend / divisor)",
                          example = "2")
    private int divisor;

    public DivideRequest(final int dividend, final int divisor)
    {
        this.dividend = dividend;
        this.divisor = divisor;
    }

    public DivideRequest()
    {
    }

    @Override
    @OpenApiRequestHandler(summary = "Divides two numbers")
    public DivideResponse onRequest()
    {
        return listenTo(new DivideResponse());
    }

    @Override
    public Class<DivideResponse> responseType()
    {
        return DivideResponse.class;
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
                problemIf(divisor == 0, "Cannot divide by zero");
            }
        };
    }
}
