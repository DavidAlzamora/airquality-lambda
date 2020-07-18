package com.airquality.aws.aqicn;

import java.net.URI;

public class WAQIUri implements URICreator {

    private final static String URL_BASE = "http://api.waqi.info/search/?keyword=%s&token=%s";

    private final Request request;

    public WAQIUri(Request request) {
        this.request = request;
    }

    @Override
    public URI buildURI() {
        String uri = String.format(URL_BASE, request.getKeyword(), request.getToken());
        return URI.create(uri);
    }

}
