package com.telenav.kivakit.examples.lambda;

import com.telenav.kivakit.examples.lambda.requests.DivisionRequest;
import com.telenav.kivakit.microservice.Microservice;
import com.telenav.kivakit.microservice.protocols.lambda.MicroserviceLambdaService;

public class DivisionLambdaService extends MicroserviceLambdaService
{
    public DivisionLambdaService(final Microservice<?> microservice)
    {
        super(microservice);
    }

    @Override
    public void onInitialize()
    {
        // Associates the "division" lambda function (version 1.0) with the DivisionRequest
        // microservlet request handler
        mount("division", "1.0", DivisionRequest.class);
    }
}
