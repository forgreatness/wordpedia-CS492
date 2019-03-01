package com.example.android.wordspedia.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.android.wordspedia.utils.WordsUtils;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "words")
public class Wordpedia implements Serializable {
    @NonNull
    @PrimaryKey
    public String word;
    public WordsUtils.WordSearchResults generalInfo;
    public WordsUtils.PronunciationSearchResults pronunciation;
    public WordsUtils.RhymesSearchResults rhymes;
    public WordsUtils.FrequencySearchResults frequency;
}
