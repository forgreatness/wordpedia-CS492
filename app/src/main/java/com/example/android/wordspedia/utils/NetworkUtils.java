package com.example.android.wordspedia.utils;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkUtils {

    private final static String WORDSAPI_KEY = "X-RapidAPI-Key";
    private final static String WORDSAPI_VALUE = "3058b6da03msha4cf6ef8c7fc5f6p121b8ejsn96be3fa3069c";

    private static final OkHttpClient mHTTPClient = new OkHttpClient();

    public static String doHTTPGet(String url) throws IOException {
        Request req = new Request.Builder().url(url).addHeader(WORDSAPI_KEY,WORDSAPI_VALUE).build();
        Response res = mHTTPClient.newCall(req).execute();
        try {
            return res.body().string();
        } finally {
            res.close();
        }
    }
}
