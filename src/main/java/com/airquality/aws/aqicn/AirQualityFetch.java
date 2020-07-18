package com.airquality.aws.aqicn;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.google.gson.JsonObject;

public interface AirQualityFetch {

    JsonObject getAirQuality(LambdaLogger logger);

}
