package com.example.android.wordspedia;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class WordRhymesAdapter extends RecyclerView.Adapter<WordRhymesAdapter.WordRhymesViewHolder> {

    private static final String TAG = WordRhymesAdapter.class.getSimpleName();

    private Context mContext;
    private ArrayList<String> mWordRhymes ;

    WordRhymesAdapter(Context context){
        mContext = context;
    }

    public void updateWordRhymes(ArrayList<String> wordRhymes){
        mWordRhymes = wordRhymes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mWordRhymes != null){
            return mWordRhymes.size();
        }else{
            return 0;
        }
    }

    @NonNull
    @Override
    public WordRhymesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rhymes_item, parent, false);
        return new WordRhymesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordRhymesAdapter.WordRhymesViewHolder holder, int position) {
        holder.bind(mWordRhymes.get(position));
    }

    class WordRhymesViewHolder extends RecyclerView.ViewHolder {

        private TextView mRhymeWord;

        public WordRhymesViewHolder(View itemView) {
            super(itemView);
            mRhymeWord = itemView.findViewById(R.id.rhyme_word_tv);
        }

        public void bind(String rhymeWord) {
            if(rhymeWord != ""){
                mRhymeWord.setText(rhymeWord);
            }
        }
    }
}
