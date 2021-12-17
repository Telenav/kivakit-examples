package com.telenav.kivakit.examples.lambda;

import com.telenav.kivakit.examples.lambda.requests.DivisionRequest;
import com.telenav.kivakit.kernel.language.values.version.Version;
import com.telenav.kivakit.microservice.Microservice;
import com.telenav.kivakit.microservice.MicroserviceMetadata;
import com.telenav.kivakit.microservice.microservlet.MicroservletRequest;
import com.telenav.kivakit.microservice.protocols.lambda.MicroserviceLambdaService;

import java.util.Set;

public class DivisionMicroservice extends Microservice<Void>
{
    public static void main(String[] arguments)
    {
        new DivisionMicroservice().run(arguments);
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

    @Override
    public MicroserviceLambdaService onNewLambdaService()
    {
        return new DivisionLambdaService(this);
    }
}
