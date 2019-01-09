package com.bskyb.internettv.thirdparty;

import java.util.HashMap;
import java.util.Map;

public class ThirdPartyConstants {

    /**
     * MOVIES_DATA map has key as movie title id and value as parental control level
     */
    public static Map<String, String> MOVIES_DATA = new HashMap<String, String>() {{

        put("movie1", "U");
        put("movie2", "PG");
        put("movie3", "12");
        put("movie4", "15");
        put("movie5", "18");
        put("movie6", "PG");
        put("movie7", "12");
        put("movie8", "15");

    }};
}