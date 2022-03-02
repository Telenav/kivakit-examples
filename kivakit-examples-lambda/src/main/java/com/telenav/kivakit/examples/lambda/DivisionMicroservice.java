package com.telenav.kivakit.examples.lambda;

import com.telenav.kivakit.language.version.Version;
import com.telenav.kivakit.microservice.Microservice;
import com.telenav.kivakit.microservice.MicroserviceMetadata;
import com.telenav.kivakit.microservice.protocols.lambda.MicroserviceLambdaService;

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
