package com.example.android.wordspedia.utils;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkUtils {

    private final static String WORDSAPI_KEY = "X-RapidAPI-Key";
    private final static String WORDSAPI_VALUE = "";

    private final static String UNSPLASH_ACCEPT_VERSION_HEADER = "Accept-Version";
    private final static String UNSPLASH_ACCEPT_VERSION_VALUE = "v1";

    private static final OkHttpClient mHTTPClient = new OkHttpClient();

    public static String doHTTPGet(String url, String apiType) throws IOException {
        if(apiType == "WORDSAPI"){
            Request req = new Request.Builder().url(url).addHeader(WORDSAPI_KEY,WORDSAPI_VALUE).build();
            Response res = mHTTPClient.newCall(req).execute();
            try {
                return res.body().string();
            } finally {
                res.close();
            }
        }
        else if(apiType == "UNSPLASHAPI"){
            Request req = new Request.Builder().url(url).addHeader(UNSPLASH_ACCEPT_VERSION_HEADER,UNSPLASH_ACCEPT_VERSION_VALUE).build();
            Response res = mHTTPClient.newCall(req).execute();
            try {
                return res.body().string();
            } finally {
                res.close();
            }
        }
        else{
            return null;
        }
    }
}
