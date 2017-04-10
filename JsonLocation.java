package com.iam.ewent;

import org.json.JSONObject;

/**
 * Created by tomato on 10.04.17.
 */
public class JsonLocation {

    JSONObject loc;

    JsonLocation(String location){
        loc = new JSONObject(location);
    }

    public String coord(){

        return loc.getDouble("latitude") + ", " + loc.getDouble("longitude");
    }

    public String city(){
        return loc.getString("city");
    }

    public String address(){
        return loc.getString("street");
    }

    public String cover(){
        JSONObject cov = loc.getJSONObject("cover");
        return cov.getString("source");

    }
}
