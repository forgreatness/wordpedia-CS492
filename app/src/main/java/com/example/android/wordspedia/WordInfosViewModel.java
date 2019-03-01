package com.example.android.wordspedia;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.wordspedia.utils.NetworkUtils;
import com.example.android.wordspedia.utils.WordsUtils;

import java.io.IOException;

public class WordInfosViewModel extends ViewModel {

    private final static  String TAG = WordInfosViewModel.class.getSimpleName();
    private MutableLiveData<String> mSearchResultsJSON;
    private String mURL;
    private ProgressBar mLoadingIndicatorPB;

    public WordInfosViewModel(ProgressBar loadingIncdicatorPB, String url){
        mLoadingIndicatorPB = loadingIncdicatorPB;
        mURL = url;
        mSearchResultsJSON = new MutableLiveData<String>();
        loadSearchResults();
    }

    private void loadSearchResults(){
        new AsyncTask<Void, Void, String>(){
            protected void onPreExecute() {
                super.onPreExecute();
                mLoadingIndicatorPB.setVisibility(View.VISIBLE);
            }

            @Override
            protected String doInBackground(Void... voids) {
                String wordInfosJSON = null;
                try {
                    wordInfosJSON = NetworkUtils.doHTTPGet(mURL);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return wordInfosJSON;
            }

            @Override
            protected void onPostExecute(String forecastJSON) {
                mSearchResultsJSON.setValue(forecastJSON);
            }
        }.execute();
    }

    public void updateURL(String url){
        if(!mURL.equals(url)){
            mURL = url;
            loadSearchResults();
        }else{
            if(mSearchResultsJSON.getValue() == ""){
                loadSearchResults();
            }
        }
    }

    public void updateURLRandom(String url){
        mURL = url;
        loadSearchResults();
    }

    public LiveData<String> getSearchResults(){
        return mSearchResultsJSON;
    }

}
