package com.example.android.wordspedia.utils;

import android.net.Uri;
import com.google.gson.Gson;
import java.io.Serializable;
import java.util.ArrayList;

public class WordsUtils {
    private final static String WORDSAPI_SERVER_PATH = "https://wordsapiv1.p.mashape.com/words/";
    private final static String WORDSAPI_RHYMES_PATH = "rhymes";
    private final static String WORDSAPI_FREQUENCY_PATH = "frequency";
    private final static String WORDSAPI_RANDOM_PARAM = "random";
    private final static String WORDSAPI_RANDOM_VALUE = "true";

    public static class WordSearchResults implements Serializable {
        public String word;
        public ArrayList<WordResults> results;
    }

    public static class WordResults {
        public String definition;
        public String partOfSpeech;
        public ArrayList<String> synonyms;
        public ArrayList<String> antonyms;
        public ArrayList<String> examples;
        public ArrayList<String> similarTo;
        public ArrayList<String> also;
        public ArrayList<String> entails;
        public ArrayList<String> pertainsTo;
        public ArrayList<String> typeOf;
        public ArrayList<String> hasTypes;
        public ArrayList<String> partOf;
        public ArrayList<String> hasParts;
        public ArrayList<String> instanceOf;
        public ArrayList<String> hasInstances;
        public ArrayList<String> memberOf;
        public ArrayList<String> hasMembers;
        public ArrayList<String> substanceOf;
        public ArrayList<String> hasSubstances;
        public ArrayList<String> inCategory;
        public ArrayList<String> hasCategories;
        public ArrayList<String> usageOf;
        public ArrayList<String> hasUsages;
        public ArrayList<String> inRegion;
        public ArrayList<String> regionOf;
    }

    public static String buildWordSearchURL(String word){
        return Uri.parse(WORDSAPI_SERVER_PATH).buildUpon()
                .appendPath(word)
                .build()
                .toString();
    }

    public static WordSearchResults parseWordSearchResults(String json){
        Gson gson = new Gson();
        WordSearchResults results = gson.fromJson(json, WordSearchResults.class);
        return results;
    }

    public static String buildRandomWordSearchURL(){
        return Uri.parse(WORDSAPI_SERVER_PATH).buildUpon()
                .appendQueryParameter(WORDSAPI_RANDOM_PARAM, WORDSAPI_RANDOM_VALUE)
                .build()
                .toString();
    }

    public static class PronunciationSearchResults implements Serializable {
        public Syllables syllables;
        public Pronunciation pronunciation;
    }

    public static class Syllables{
        public int count;
        public ArrayList<String> list;
    }

    public static class Pronunciation{
        public String noun;
        public String verb;
        public String all;
    }

    public static String buildPronunciationSearchURL(String word){
        return Uri.parse(WORDSAPI_SERVER_PATH).buildUpon()
                .appendPath(word)
                .build()
                .toString();
    }

    public static PronunciationSearchResults parsePronunciationSearchResults(String json){
        Gson gson = new Gson();
        PronunciationSearchResults results = gson.fromJson(json, PronunciationSearchResults.class);
        return results;
    }

    public static class RhymesSearchResults implements Serializable {
        public String word;
        public Rhymes rhymes;
    }


    public static class Rhymes{
        public ArrayList<String> all;
        public ArrayList<String> noun;
        public ArrayList<String> verb;
    }

    public static String buildRhymesSearchURL(String word){
        return Uri.parse(WORDSAPI_SERVER_PATH).buildUpon()
                .appendPath(word)
                .appendPath(WORDSAPI_RHYMES_PATH)
                .build()
                .toString();
    }

    public static RhymesSearchResults parseRhymesSearchResults(String json){
        Gson gson = new Gson();
        RhymesSearchResults results = gson.fromJson(json, RhymesSearchResults.class);
        return results;
    }

    public static class FrequencySearchResults implements Serializable {
        public String word;
        public Frequency frequency;
    }

    public static class Frequency{
        public float zipf;
        public float perMillion;
        public float diversity;
    }

    public static String buildFrequencySearchURL(String word){
        return Uri.parse(WORDSAPI_SERVER_PATH).buildUpon()
                .appendPath(word)
                .appendPath(WORDSAPI_FREQUENCY_PATH)
                .build()
                .toString();
    }

    public static FrequencySearchResults parseFrequencySearchResults(String json){
        Gson gson = new Gson();
        FrequencySearchResults results = gson.fromJson(json, FrequencySearchResults.class);
        return results;
    }
}
