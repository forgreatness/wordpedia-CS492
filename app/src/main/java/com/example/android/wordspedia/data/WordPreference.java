package com.example.android.wordspedia.data;

public class WordPreference {

    private static String Word = "";

    public static void setWord(String word){
        Word = word;
    }

    public static String getWord(){
        return Word;
    }
}
