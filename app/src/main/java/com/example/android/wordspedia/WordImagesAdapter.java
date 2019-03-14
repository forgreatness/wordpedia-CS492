package com.example.android.wordspedia;

import android.content.Context;
import android.gesture.GestureLibraries;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.wordspedia.data.GitHubRepo;
import com.example.android.wordspedia.utils.UnsplashUtils;
import com.example.android.wordspedia.utils.WordsUtils;

import java.util.ArrayList;

public class WordImagesAdapter extends RecyclerView.Adapter<WordImagesAdapter.WordImagesViewHolder> {
    private static final String TAG = WordImagesAdapter.class.getSimpleName();

    private Context mContext;
    private ArrayList<UnsplashUtils.UnsplashImageItem> mWordImages ;

    WordImagesAdapter(Context context){
        mContext = context;
    }

    public void updateWordImages(ArrayList<UnsplashUtils.UnsplashImageItem> wordImages){
        mWordImages = wordImages;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mWordImages != null){
            return mWordImages.size();
        }else{
            return 0;
        }
    }

    @NonNull
    @Override
    public WordImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.image_item, parent, false);
        return new WordImagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordImagesAdapter.WordImagesViewHolder holder, int position) {
        holder.bind(mWordImages.get(position));
    }

    class WordImagesViewHolder extends RecyclerView.ViewHolder {
        private TextView mImageDescription;
        private ImageView mImageOfWord;

        public WordImagesViewHolder(View itemView) {
            super(itemView);
            mImageDescription = itemView.findViewById(R.id.image_description);
            mImageOfWord = itemView.findViewById(R.id.image_of_word);
        }

        public void bind(UnsplashUtils.UnsplashImageItem imageItem) {
            String imageDescription = "";
            if(imageItem.description != null){
                imageDescription = imageDescription + imageItem.description;
                if(imageItem.alt_description != null){
                    imageDescription = imageDescription + " OR " + imageItem.alt_description;
                }
                mImageDescription.setText(imageDescription);
                mImageDescription.setVisibility(View.VISIBLE);
            }else{
                if(imageItem.alt_description != null){
                    imageDescription = imageDescription + imageItem.alt_description;
                    mImageDescription.setVisibility(View.VISIBLE);
                    mImageDescription.setText(imageDescription);
                }else{
                    mImageDescription.setVisibility(View.GONE);
                }
            }

            if(imageItem.urls.raw != null){
                Glide.with(mImageOfWord.getContext()).load(imageItem.urls.raw).into(mImageOfWord);
                mImageOfWord.setVisibility(View.VISIBLE);
            }else{
                mImageOfWord.setVisibility(View.GONE);
            }
        }
    }
}
