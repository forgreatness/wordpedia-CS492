package com.example.android.wordspedia;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.wordspedia.utils.NetworkUtils;

import java.io.IOException;

public class WordViewModel extends ViewModel {

    private final static  String TAG = WordViewModel.class.getSimpleName();
    private MutableLiveData<String> mSearchResultsJSON;
    private String mURL;
    private String mSourceOfContent;
    private ProgressBar mLoadingIndicatorPB;

    public WordViewModel(ProgressBar loadingIncdicatorPB, String url, String sourceOfContent){
        mLoadingIndicatorPB = loadingIncdicatorPB;
        mURL = url;
        mSourceOfContent = sourceOfContent;
        mSearchResultsJSON = new MutableLiveData<String>();
        loadSearchResults();
    }

    private void loadSearchResults(){
        new AsyncTask<Void, Void, String>(){
            protected void onPreExecute() {
                super.onPreExecute();
                if(mLoadingIndicatorPB != null){
                    mLoadingIndicatorPB.setVisibility(View.VISIBLE);
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                String searchResultsJSON = null;
                try {
                    searchResultsJSON = NetworkUtils.doHTTPGet(mURL, mSourceOfContent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return searchResultsJSON;
            }

            @Override
            protected void onPostExecute(String searchResultsJSON) {
                mSearchResultsJSON.setValue(searchResultsJSON);
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
