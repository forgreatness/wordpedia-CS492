package com.example.android.wordspedia;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.wordspedia.data.WordPreference;
import com.example.android.wordspedia.utils.UnsplashUtils;
import com.example.android.wordspedia.utils.WordsUtils;

public class WordRhymesActivity extends AppCompatActivity {

    private static final String TAG = WordRhymesActivity.class.getSimpleName();
    private static final String ACTIVITY_SOURCE_OF_CONTENT = "WORDSAPI";

    private RecyclerView mAllRhymesWordRV;
    private RecyclerView mNounRhymesWordRV;
    private RecyclerView mVerbRhymesWordRV;

    private WordRhymesAdapter mAllRhymesAdapter;
    private WordRhymesAdapter mNounRhymesAdapter;
    private WordRhymesAdapter mVerbRhymesAdapter;

    private TextView mWordTV;
    private TextView mAllRhymesHeaderTV;
    private TextView mNounRhymesHeaderTV;
    private TextView mVerbRhymesHeaderTV;

    private WordViewModel mWordRhymesViewModel;

    private WordsUtils.RhymesSearchResults mWordRhymesSearchResults;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_rhymes);

        mAllRhymesWordRV = findViewById(R.id.rv_all_rhymes_words);
        mNounRhymesWordRV = findViewById(R.id.rv_noun_rhymes_words);
        mVerbRhymesWordRV = findViewById(R.id.rv_verb_rhymes_words);

        mWordTV  = findViewById(R.id.word_tv);
        mAllRhymesHeaderTV = findViewById(R.id.all_rhymes_header_tv);
        mNounRhymesHeaderTV = findViewById(R.id.noun_rhymes_header_tv);
        mVerbRhymesHeaderTV = findViewById(R.id.verb_rhymes_header_tv);

        mAllRhymesWordRV.setLayoutManager(new LinearLayoutManager(this));
        mAllRhymesWordRV.setHasFixedSize(true);

        mVerbRhymesWordRV.setLayoutManager(new LinearLayoutManager(this));
        mVerbRhymesWordRV.setHasFixedSize(true);

        mNounRhymesWordRV.setLayoutManager(new LinearLayoutManager(this));
        mNounRhymesWordRV.setHasFixedSize(true);

        mAllRhymesAdapter = new WordRhymesAdapter(this);
        mAllRhymesWordRV.setAdapter(mAllRhymesAdapter);

        mNounRhymesAdapter = new WordRhymesAdapter(this);
        mNounRhymesWordRV.setAdapter(mNounRhymesAdapter);

        mVerbRhymesAdapter = new WordRhymesAdapter(this);
        mVerbRhymesWordRV.setAdapter(mVerbRhymesAdapter);

        mWordRhymesViewModel = ViewModelProviders.of(this, new ViewModelFactory(null, getURL(WordPreference.getWord()), ACTIVITY_SOURCE_OF_CONTENT)).get(WordViewModel.class);
        mWordRhymesViewModel.getSearchResults().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String searchResultsJSON) {
                if (searchResultsJSON != null) {
                    mWordTV.setText(WordPreference.getWord());
                    mWordRhymesSearchResults = WordsUtils.parseRhymesSearchResults(searchResultsJSON);
                    if(mWordRhymesSearchResults.rhymes != null){
                        if(mWordRhymesSearchResults.rhymes.all != null){
                            mAllRhymesAdapter.updateWordRhymes(mWordRhymesSearchResults.rhymes.all);
                            mAllRhymesHeaderTV.setVisibility(View.VISIBLE);
                            mAllRhymesWordRV.setVisibility(View.VISIBLE);
                        }else{
                            mAllRhymesHeaderTV.setVisibility(View.GONE);
                            mAllRhymesWordRV.setVisibility(View.GONE);
                        }

                        if(mWordRhymesSearchResults.rhymes.noun != null){
                            mNounRhymesAdapter.updateWordRhymes(mWordRhymesSearchResults.rhymes.noun);
                            mNounRhymesHeaderTV.setVisibility(View.VISIBLE);
                            mNounRhymesWordRV.setVisibility(View.VISIBLE);
                        }else{
                            mNounRhymesHeaderTV.setVisibility(View.GONE);
                            mNounRhymesWordRV.setVisibility(View.GONE);
                        }

                        if(mWordRhymesSearchResults.rhymes.verb != null){
                            mVerbRhymesAdapter.updateWordRhymes(mWordRhymesSearchResults.rhymes.verb);
                            mVerbRhymesHeaderTV.setVisibility(View.VISIBLE);
                            mVerbRhymesWordRV.setVisibility(View.VISIBLE);
                        }else{
                            mVerbRhymesHeaderTV.setVisibility(View.GONE);
                            mVerbRhymesWordRV.setVisibility(View.GONE);
                        }
                    }
                    else{
                        mAllRhymesHeaderTV.setVisibility(View.GONE);
                        mNounRhymesHeaderTV.setVisibility(View.GONE);
                        mVerbRhymesHeaderTV.setVisibility(View.GONE);
                        mAllRhymesWordRV.setVisibility(View.GONE);
                        mNounRhymesWordRV.setVisibility(View.GONE);
                        mVerbRhymesWordRV.setVisibility(View.GONE);
                    }
                } else {
                    mAllRhymesHeaderTV.setVisibility(View.GONE);
                    mNounRhymesHeaderTV.setVisibility(View.GONE);
                    mVerbRhymesHeaderTV.setVisibility(View.GONE);
                    mAllRhymesWordRV.setVisibility(View.GONE);
                    mNounRhymesWordRV.setVisibility(View.GONE);
                    mVerbRhymesWordRV.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWordRhymesViewModel.updateURL(getURL(WordPreference.getWord()));
    }

    public String getURL(String word){
        String wordsAPIRequestURL = WordsUtils.buildRhymesSearchURL(word);
        Log.d(TAG, wordsAPIRequestURL);
        return wordsAPIRequestURL;
    }

}
