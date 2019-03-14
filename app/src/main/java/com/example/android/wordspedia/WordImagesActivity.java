package com.example.android.wordspedia;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.wordspedia.data.WordPreference;
import com.example.android.wordspedia.utils.UnsplashUtils;
import com.example.android.wordspedia.utils.WordsUtils;

public class WordImagesActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = WordImagesActivity.class.getSimpleName();
    private static final String ACTIVITY_SOURCE_OF_CONTENT = "UNSPLASHAPI";

    private RecyclerView mWordImagesRV;
    private WordImagesAdapter mWordImagesAdapter;
    private TextView mLoadingErrorTV;
    private TextView mWordTV;
    private ProgressBar mLoadingIndicatorPB;
    private WordViewModel mWordImagesViewModel;

    private String mWord;

    private UnsplashUtils.UnsplashSearchResults mWordImagesSearchResults;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_images);

        mWordImagesRV = findViewById(R.id.rv_unsplash_images);
        mWordTV = findViewById(R.id.word_tv);
        mLoadingErrorTV = findViewById(R.id.tv_loading_error);
        mLoadingIndicatorPB = findViewById(R.id.pb_loading);

        mWordImagesRV.setLayoutManager(new LinearLayoutManager(this));
        mWordImagesRV.setHasFixedSize(true);

        mWordImagesAdapter = new WordImagesAdapter(this);
        mWordImagesRV.setAdapter(mWordImagesAdapter);

        mWordImagesViewModel = ViewModelProviders.of(this, new ViewModelFactory(mLoadingIndicatorPB, getURL(WordPreference.getWord()), ACTIVITY_SOURCE_OF_CONTENT)).get(WordViewModel.class);
        mWordImagesViewModel.getSearchResults().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String searchResultsJSON) {
                mLoadingIndicatorPB.setVisibility(View.INVISIBLE);
                if (searchResultsJSON != null) {
                    mLoadingErrorTV.setVisibility(View.INVISIBLE);
                    mWordImagesRV.setVisibility(View.VISIBLE);
                    mWordImagesSearchResults = UnsplashUtils.parseUnsplashAPISearchResults(searchResultsJSON);
                    mWordTV.setText(WordPreference.getWord());
                    mWordImagesAdapter.updateWordImages(mWordImagesSearchResults.results);
                } else {
                    mLoadingErrorTV.setVisibility(View.VISIBLE);
                    mWordImagesRV.setVisibility(View.INVISIBLE);
                }
                mLoadingIndicatorPB.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    protected void onDestroy() {
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWordImagesViewModel.updateURL(getURL(WordPreference.getWord()));
    }

    public String getURL(String word){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String numOfImages = sharedPreferences.getString(
                getString(R.string.pref_num_of_images_key),
                getString(R.string.pref_num_of_images_default));

        String unsplashAPIRequestURL = UnsplashUtils.buildUnsplashSearchURL(word, numOfImages);
        Log.d(TAG, unsplashAPIRequestURL);
        return unsplashAPIRequestURL;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        String numOfImages = sharedPreferences.getString(
                getString(R.string.pref_num_of_images_key),
                getString(R.string.pref_num_of_images_default));

        String unsplashAPIRequestURL = UnsplashUtils.buildUnsplashSearchURL(WordPreference.getWord(), numOfImages);
        Log.d(TAG, unsplashAPIRequestURL);
        mWordImagesViewModel.updateURL(unsplashAPIRequestURL);
    }
}
