package com.example.android.wordspedia;

import android.animation.ArgbEvaluator;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.media.Rating;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.android.wordspedia.data.WordPreference;
import com.example.android.wordspedia.utils.UnsplashUtils;
import com.example.android.wordspedia.utils.WordsUtils;

public class WordRatingActivity extends AppCompatActivity {

    private static final String TAG = WordRatingActivity.class.getSimpleName();
    private static final String ACTIVITY_SOURCE_OF_CONTENT = "WORDSAPI";

    private LinearLayout mFrequencyLV;
    private LinearLayout mUsagePerMillionLV;
    private LinearLayout mDifficultyLV;

    private TextView mUsagePerMillionTV;
    private TextView mDifficultyTV;
    private TextView mWordTV;
    private RatingBar mFrequencyRB;

    private WordViewModel mWordRatingViewModel;
    private ArgbEvaluator mArgbEvaluator;
    private int mDifficultyStartColor;
    private int mDifficultyEndColor;
    private int mDifficultyColor;

    private WordsUtils.FrequencySearchResults mWordRatingSearchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_rating);

        mUsagePerMillionTV = findViewById(R.id.usage_per_million_tv);
        mDifficultyTV = findViewById(R.id.difficulty_tv);
        mWordTV = findViewById(R.id.word_tv);
        mFrequencyRB = findViewById(R.id.frequency_rating_bar);

        mFrequencyLV = findViewById(R.id.frequency_section);
        mUsagePerMillionLV = findViewById(R.id.usage_per_million_section);
        mDifficultyLV = findViewById(R.id.difficulty_section);

        mArgbEvaluator = new ArgbEvaluator();
        mDifficultyStartColor = Color.parseColor("red");
        mDifficultyEndColor = Color.parseColor("green");

        mWordRatingViewModel = ViewModelProviders.of(this, new ViewModelFactory(null, getURL(WordPreference.getWord()), ACTIVITY_SOURCE_OF_CONTENT)).get(WordViewModel.class);
        mWordRatingViewModel.getSearchResults().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String searchResultsJSON) {
                if (searchResultsJSON != null) {
                    mWordRatingSearchResults = WordsUtils.parseFrequencySearchResults(searchResultsJSON);
                    mWordTV.setText(WordPreference.getWord());
                    if(mWordRatingSearchResults.frequency != null){
                        mFrequencyRB.setRating(mWordRatingSearchResults.frequency.zipf);
                        mUsagePerMillionTV.setText(Float.toString(mWordRatingSearchResults.frequency.perMillion));
                        mDifficultyColor = (Integer) mArgbEvaluator.evaluate(mWordRatingSearchResults.frequency.diversity, mDifficultyStartColor, mDifficultyEndColor);
                        mDifficultyTV.setBackgroundColor(mDifficultyColor);
                        mDifficultyTV.setText(Float.toString((1 - mWordRatingSearchResults.frequency.diversity) * 100));

                        mFrequencyLV.setVisibility(View.VISIBLE);
                        mUsagePerMillionLV.setVisibility(View.VISIBLE);
                        mDifficultyLV.setVisibility(View.VISIBLE);
                    }else{
                        mFrequencyLV.setVisibility(View.GONE);
                        mUsagePerMillionLV.setVisibility(View.GONE);
                        mDifficultyLV.setVisibility(View.GONE);
                    }
                } else{
                    mFrequencyLV.setVisibility(View.GONE);
                    mUsagePerMillionLV.setVisibility(View.GONE);
                    mDifficultyLV.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWordRatingViewModel.updateURL(getURL(WordPreference.getWord()));
    }

    public String getURL(String word){
        String wordsAPIRequestURL = WordsUtils.buildFrequencySearchURL(word);
        Log.d(TAG, wordsAPIRequestURL);
        return wordsAPIRequestURL;
    }

}
