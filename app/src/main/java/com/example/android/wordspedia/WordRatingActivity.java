package com.example.android.wordspedia;

import android.animation.ArgbEvaluator;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.media.Rating;
import android.support.annotation.Nullable;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.android.wordspedia.data.WordPreference;
import com.example.android.wordspedia.utils.UnsplashUtils;
import com.example.android.wordspedia.utils.WordsUtils;

public class WordRatingActivity extends AppCompatActivity {

    private final String BULLETS_POINTS_UNICODE = "\u2022";
    private final String NEW_LINE_UNICODE = "\n";
    private final String NDASH_UNICODE = "\u2013";
    private final String COMMAS_UNICODE = "\u002c";
    private final String SPACES = "\u0020";

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.word_rating_activity_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.option_share:
                shareWordRating();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void shareWordRating(){
        if(mWordRatingSearchResults.frequency != null){
            ShareCompat.IntentBuilder.from(this)
                    .setType("text/plain")
                    .setSubject(mWordRatingSearchResults.word + " Rating")
                    .setText(mWordRatingSearchResults.word + " Rating" + NEW_LINE_UNICODE +
                            "Frequency: " + mWordRatingSearchResults.frequency.zipf + NEW_LINE_UNICODE +
                            "Usage Per Million Words: " + mWordRatingSearchResults.frequency.perMillion + NEW_LINE_UNICODE +
                            "Difficulty: " + mWordRatingSearchResults.frequency.diversity)
                    .setChooserTitle("How would you like to share this Word")
                    .startChooser();

        }
    }

}
