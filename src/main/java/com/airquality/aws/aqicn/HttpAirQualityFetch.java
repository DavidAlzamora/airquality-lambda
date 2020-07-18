package com.airquality.aws.aqicn;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Optional;

public class HttpAirQualityFetch implements AirQualityFetch {

    @Override
    public JsonObject getAirQuality(LambdaLogger logger) {
        Optional<String> optionalResponse = Optional.ofNullable(doRequest(logger));
        if (optionalResponse.isPresent()){
            String json = optionalResponse.get();
            return JsonParser.parseString(json).getAsJsonObject();
        }
        return null;
    }

    private String doRequest(LambdaLogger logger){
        URICreator uriCreator = new WAQIUri(new ConfigRequest());
        HttpRequest httpRequest = HttpRequest.newBuilder(uriCreator.buildURI())
                                             .header("Content-Encoding", "gzip")
                                             .GET()
                                             .build();

        HttpClient httpClient = HttpClient.newBuilder()
                                          .version(HttpClient.Version.HTTP_1_1)
                                          .followRedirects(HttpClient.Redirect.NORMAL)
                                          .connectTimeout(Duration.ofSeconds(2))
                                          .build();

        try {
            logger.log(httpRequest.uri().toString());
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return httpResponse.body();
        } catch (IOException | InterruptedException e) {
            logger.log("Algo ha ido mal");
            logger.log(e.getMessage());
            logger.log(e.getLocalizedMessage());
            return null;
        }
    }


}
