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

package com.telenav.kivakit.examples.microservice;

import com.telenav.kivakit.core.version.Version;
import com.telenav.kivakit.examples.microservice.requests.DivisionRequest;
import com.telenav.kivakit.microservice.Microservice;
import com.telenav.kivakit.microservice.protocols.rest.http.RestService;

import static com.telenav.kivakit.core.version.Version.version;
import static com.telenav.kivakit.network.http.HttpMethod.POST;

/**
 * REST service that provides arithmetic division
 *
 * @author jonathanl (shibo)
 */
public class DivisionRestService extends RestService
{
    public DivisionRestService(Microservice<?> microservice)
    {
        super(microservice);
    }

    @Override
    public Version apiVersion()
    {
        return version("1.0");
    }

    @Override
    public void onInitialize()
    {
        mount(apiVersion(), "/divide", POST, DivisionRequest.class);
    }
}
