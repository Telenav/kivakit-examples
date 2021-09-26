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

import com.telenav.kivakit.examples.microservice.rest.requests.CreateLexakaiPullRequest;
import com.telenav.kivakit.microservice.Microservice;
import com.telenav.kivakit.microservice.rest.MicroserviceRestApplication;

/**
 * REST interface for the Lexakai microservice example
 *
 * @author jonathanl (shibo)
 */
public class LexakaiRestApplication extends MicroserviceRestApplication
{
    /**
     * Construct and mount requests
     */
    public LexakaiRestApplication(Microservice microservice)
    {
        super(microservice);

        mount("api/1.0/create-pull-request", CreateLexakaiPullRequest.class);
    }
}
