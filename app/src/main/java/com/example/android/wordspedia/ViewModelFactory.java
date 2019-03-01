package com.example.android.wordspedia;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.widget.ProgressBar;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private ProgressBar mLoadingIndicatorPB;
    private String mUrl;

    public ViewModelFactory(ProgressBar loadingIndicatorPB, String url){
        mLoadingIndicatorPB = loadingIndicatorPB;
        mUrl = url;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new WordInfosViewModel (mLoadingIndicatorPB, mUrl);
    }
}
