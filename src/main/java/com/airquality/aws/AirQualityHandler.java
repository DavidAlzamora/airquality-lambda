package com.airquality.aws;

import com.airquality.aws.aqicn.AirQualityFetch;
import com.airquality.aws.aqicn.HttpAirQualityFetch;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Optional;

public class AirQualityHandler implements RequestHandler<String, String> {

    private final AirQualityFetch airQualityFetch;

    public AirQualityHandler() {
        this.airQualityFetch = new HttpAirQualityFetch();
    }

    @Override
    public String handleRequest(String input, Context context) {
        LambdaLogger logger = context.getLogger();
        Optional<JsonObject> optionalJsonObject = Optional.ofNullable(airQualityFetch.getAirQuality(logger));
        if (optionalJsonObject.isPresent()) {
            JsonObject airQuality = optionalJsonObject.get();
            return  airQuality.toString();
        } else {
            return "Error";
        }
    }

}
