package com.example.android.wordspedia;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.widget.ProgressBar;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private ProgressBar mLoadingIndicatorPB;
    private String mUrl;
    private String mSourceOfContent;

    public ViewModelFactory(ProgressBar loadingIndicatorPB, String url, String sourceOfContent){
        mLoadingIndicatorPB = loadingIndicatorPB;
        mUrl = url;
        mSourceOfContent = sourceOfContent;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new WordViewModel(mLoadingIndicatorPB, mUrl, mSourceOfContent);
    }
}
