package com.telenav.kivakit.examples.lambda;

import com.telenav.kivakit.examples.lambda.requests.DivisionRequest;
import com.telenav.kivakit.kernel.language.values.version.Version;
import com.telenav.kivakit.microservice.Microservice;
import com.telenav.kivakit.microservice.MicroserviceMetadata;
import com.telenav.kivakit.microservice.microservlet.MicroservletRequest;

import java.util.Set;

public class DivisionMicroservice extends Microservice<Void>
{
    public static void main(String[] arguments)
    {
        new DivisionMicroservice().run(arguments);
    }

    @Override
    public Set<Class<? extends MicroservletRequest>> allowedLambdaRequests()
    {
        return Set.of(DivisionRequest.class);
    }

    /**
     * @return Metadata describing this microservice
     */
    @Override
    public MicroserviceMetadata metadata()
    {
        return new MicroserviceMetadata()
                .withName("division-microservice")
                .withDescription("Example microservice performing arithmetic division")
                .withVersion(Version.parse(this, "1.0"));
    }
}
