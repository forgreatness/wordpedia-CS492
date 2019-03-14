package com.example.android.wordspedia.utils;

import android.net.Uri;

import com.google.gson.Gson;

import java.util.ArrayList;

public class UnsplashUtils {

    private final static String UNSPLASH_API_SERVER_PATH = "https://api.unsplash.com/search/photos";
    private final static String UNSPLASH_API_PAGE_PARAM = "page";
    private final static String UNSPLASH_API_PAGE_VALUE = "1";
    private final static String UNSPLASH_API_PER_PAGE_PARAM = "per_page";
    private final static String UNSPLASH_API_QUERY_PARAM = "query";
    private final static String UNSPLASH_API_CLIENT_ID_PARAM = "client_id";
    private final static String UNSPLASH_API_CLIENT_SECRET_PARAM = "client_secret";
    private final static String UNSPLASH_API_CLIENT_ID_VALUE = "90af595032ce23870f4e3527b561b0b8982225938d398fbee16920680dc79535";
    private final static String UNSPLASH_API_CLIENT_SECRET_VALUE = "";

    public static class UnsplashSearchResults{
        public ArrayList<UnsplashImageItem> results;
    }

    public static class UnsplashImageItem{
        public String description;
        public String alt_description;
        public UnsplashImageUrl urls;
    }

    public static class UnsplashImageUrl{
        public String raw;
    }

    public static String buildUnsplashSearchURL(String word, String numOfImages){
        return Uri.parse(UNSPLASH_API_SERVER_PATH).buildUpon()
                .appendQueryParameter(UNSPLASH_API_QUERY_PARAM, word)
                .appendQueryParameter(UNSPLASH_API_PAGE_PARAM, UNSPLASH_API_PAGE_VALUE)
                .appendQueryParameter(UNSPLASH_API_PER_PAGE_PARAM, numOfImages)
                .appendQueryParameter(UNSPLASH_API_CLIENT_ID_PARAM, UNSPLASH_API_CLIENT_ID_VALUE)
                .appendQueryParameter(UNSPLASH_API_CLIENT_SECRET_PARAM, UNSPLASH_API_CLIENT_SECRET_VALUE)
                .build()
                .toString();
    }

    public static UnsplashSearchResults parseUnsplashAPISearchResults(String json){
        Gson gson = new Gson();
        UnsplashSearchResults results = gson.fromJson(json, UnsplashSearchResults.class);
        return results;
    }
}
