package com.example.android.wordspedia;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.wordspedia.data.WordPreference;
import com.example.android.wordspedia.utils.WordsUtils;

public class WordPronunciationActivity extends AppCompatActivity {

    private static final String TAG = WordPronunciationActivity.class.getSimpleName();
    private static final String ACTIVITY_SOURCE_OF_CONTENT = "WORDSAPI";

    private TextView mWordTV;
    private TextView mAllPronunciationTV;
    private TextView mNounPronunciationTV;
    private TextView mVerbPronunciationTV;

    private LinearLayout mAllPronunciationSection;
    private LinearLayout mNounPronunciationSection;
    private LinearLayout mVerbPronunciationSection;

    private WordViewModel mWordPronunciationViewModel;

    private WordsUtils.PronunciationSearchResults mWordPronunciationSearchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_pronunciation);

        mWordTV = findViewById(R.id.word_tv);
        mAllPronunciationTV = findViewById(R.id.all_pronunciation_tv);
        mNounPronunciationTV = findViewById(R.id.noun_pronunciation_tv);
        mVerbPronunciationTV = findViewById(R.id.verb_pronunciation_tv);

        mAllPronunciationSection = findViewById(R.id.all_pronunciation_section);
        mNounPronunciationSection = findViewById(R.id.noun_pronunciation_section);
        mVerbPronunciationSection = findViewById(R.id.verb_pronunciation_section);

        mWordPronunciationViewModel = ViewModelProviders.of(this, new ViewModelFactory(null, getURL(WordPreference.getWord()), ACTIVITY_SOURCE_OF_CONTENT)).get(WordViewModel.class);
        mWordPronunciationViewModel.getSearchResults().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String searchResultsJSON) {
                if (searchResultsJSON != null) {
                    mWordPronunciationSearchResults = WordsUtils.parsePronunciationSearchResults(searchResultsJSON);
                    mWordTV.setText(WordPreference.getWord());
                    if(mWordPronunciationSearchResults.pronunciation != null){
                        if(mWordPronunciationSearchResults.pronunciation.all != null){
                            mAllPronunciationTV.setText(mWordPronunciationSearchResults.pronunciation.all);
                            mAllPronunciationSection.setVisibility(View.VISIBLE);
                        }else{
                            mAllPronunciationSection.setVisibility(View.GONE);
                        }

                        if(mWordPronunciationSearchResults.pronunciation.noun != null){
                            mNounPronunciationTV.setText(mWordPronunciationSearchResults.pronunciation.noun);
                            mNounPronunciationSection.setVisibility(View.VISIBLE);
                        }else{
                            mNounPronunciationSection.setVisibility(View.GONE);
                        }

                        if(mWordPronunciationSearchResults.pronunciation.verb != null){
                            mVerbPronunciationTV.setText(mWordPronunciationSearchResults.pronunciation.verb);
                            mVerbPronunciationSection.setVisibility(View.VISIBLE);
                        }else{
                            mVerbPronunciationSection.setVisibility(View.GONE);
                        }
                    }else{
                        mAllPronunciationSection.setVisibility(View.GONE);
                        mNounPronunciationSection.setVisibility(View.GONE);
                        mVerbPronunciationSection.setVisibility(View.GONE);
                    }
                } else{
                    mAllPronunciationSection.setVisibility(View.GONE);
                    mNounPronunciationSection.setVisibility(View.GONE);
                    mVerbPronunciationSection.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWordPronunciationViewModel.updateURL(getURL(WordPreference.getWord()));
    }

    public String getURL(String word){
        String wordsAPIRequestURL = WordsUtils.buildPronunciationSearchURL(word);
        Log.d(TAG, wordsAPIRequestURL);
        return wordsAPIRequestURL;
    }

}
