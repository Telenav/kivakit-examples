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
import com.telenav.kivakit.microservice.microservlet.BaseMicroservletRequest;
import com.telenav.kivakit.microservice.microservlet.BaseMicroservletResponse;
import com.telenav.kivakit.microservice.microservlet.MicroservletRequest;
import com.telenav.kivakit.microservice.microservlet.MicroservletResponse;
import com.telenav.kivakit.validation.BaseValidator;
import com.telenav.kivakit.validation.ValidationType;
import com.telenav.kivakit.validation.Validator;

/**
 * A {@link MicroservletRequest} that performs arithmetic division
 *
 * @author jonathanl (shibo)
 * @see MicroservletRequest
 * @see MicroservletResponse
 */
public class DivisionRequest extends BaseMicroservletRequest
{
    public class DivisionResponse extends BaseMicroservletResponse
    {
        @Tag(1)
        @Expose
        int quotient;

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
            return Validator.nullValidator();
        }
    }

    @Tag(1)
    @Expose
    private int dividend;

    @Tag(2)
    @Expose
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
