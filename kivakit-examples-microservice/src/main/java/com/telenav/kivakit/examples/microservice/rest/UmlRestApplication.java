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

import com.telenav.kivakit.component.ComponentMixin;
import com.telenav.kivakit.examples.microservice.rest.serialization.UmlGsonFactory;
import com.telenav.kivakit.kernel.language.collections.set.ObjectSet;
import com.telenav.kivakit.microservice.rest.MicroserviceRestApplication;
import com.telenav.kivakit.microservice.rest.MicroserviceRestResource;
import com.telenav.kivakit.microservice.rest.serialization.MicroserviceGsonFactory;

import javax.ws.rs.ApplicationPath;

/**
 * REST interface for the KivaKit example microservice
 *
 * @author jonathanl (shibo)
 */
@ApplicationPath("/api")
public class UmlRestApplication extends MicroserviceRestApplication implements ComponentMixin
{
    @Override
    protected MicroserviceGsonFactory gsonFactory()
    {
        return new UmlGsonFactory();
    }

    @Override
    protected ObjectSet<MicroserviceRestResource> resources()
    {
        return ObjectSet.of(new UmlRestResource());
    }
}
