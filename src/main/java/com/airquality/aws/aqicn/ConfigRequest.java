package com.airquality.aws.aqicn;

import java.util.ResourceBundle;

public class ConfigRequest implements Request {

    @Override
    public String getToken() {
        return ResourceBundle.getBundle("config").getString("token");
    }

    @Override
    public String getKeyword() {
        return ResourceBundle.getBundle("config").getString("target");
    }

}
