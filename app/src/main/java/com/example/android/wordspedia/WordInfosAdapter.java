package com.example.android.wordspedia;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.wordspedia.utils.WordsUtils;

import java.util.ArrayList;
import java.util.Random;

public class WordInfosAdapter extends RecyclerView.Adapter<WordInfosAdapter.WordInfoViewHolder> {

    private static final String TAG = WordInfosAdapter.class.getSimpleName();

    private Context mContext;
    private ArrayList<WordsUtils.WordResults> mWordInfos ;

    WordInfosAdapter(Context context){
        mContext = context;
    }

    public void updateWordInfos(ArrayList<WordsUtils.WordResults> wordInfos){
        mWordInfos = wordInfos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mWordInfos != null){
            return mWordInfos.size();
        }else{
            return 0;
        }
    }

    @NonNull
    @Override
    public WordInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.info_item, parent, false);
        return new WordInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordInfosAdapter.WordInfoViewHolder holder, int position) {
        holder.bind(mWordInfos.get(position));
    }

    class WordInfoViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout mInfoItem;
        private LinearLayout mDefinitionSection;
        private LinearLayout mPartOfSpeechSection;
        private LinearLayout mSynonymsAntonymsSection;
        private LinearLayout mSynonymsSection;
        private LinearLayout mAntonymsSection;
        private LinearLayout mTypeOfHasTypesSection;
        private LinearLayout mTypeOfSection;
        private LinearLayout mHasTypesSection;
        private LinearLayout mExamplesSection;
        private LinearLayout mPartOfHasPartsSection;
        private LinearLayout mPartOfSection;
        private LinearLayout mHasPartsSection;
        private LinearLayout mInstanceOfHasInstancesSection;
        private LinearLayout mInstanceOfSection;
        private LinearLayout mHasInstancesSection;
        private LinearLayout mMemberOfHasMembersSection;
        private LinearLayout mMemberOfSection;
        private LinearLayout mHasMembersSection;
        private LinearLayout mSubstanceOfHasSubstancesSection;
        private LinearLayout mSubstanceOfSection;
        private LinearLayout mHasSubstancesSection;

        private TextView mSynonymsTV;
        private TextView mAntonymsTV;
        private TextView mDefinitionTV;
        private TextView mPartOfSpeechTV;
        private TextView mTypeOfTV;
        private TextView mHasTypesTV;
        private TextView mExamplesTV;
        private TextView mPartOfTV;
        private TextView mHasPartsTV;
        private TextView mInstanceOfTV;
        private TextView mHasInstancesTV;
        private TextView mMemberOfTV;
        private TextView mHasMembersTV;
        private TextView mSubstanceOfTV;
        private TextView mHasSubstancesTV;

        private final String BULLETS_POINTS_UNICODE = "\u2022";
        private final String NEW_LINE_UNICODE = "\n";
        private final String NDASH_UNICODE = "\u2013";
        private final String SPACES = " ";

        public WordInfoViewHolder(View itemView){
            super(itemView);
            mInfoItem = itemView.findViewById(R.id.info_item);
            mDefinitionSection = itemView.findViewById(R.id.definition_section);
            mPartOfSpeechSection = itemView.findViewById(R.id.partofspeech_section);
            mSynonymsAntonymsSection = itemView.findViewById(R.id.synonyms_antonyms_section);
            mSynonymsSection = itemView.findViewById(R.id.synonyms_section);
            mAntonymsSection = itemView.findViewById(R.id.antonyms_section);
            mTypeOfHasTypesSection = itemView.findViewById(R.id.type_of_has_types_section);
            mTypeOfSection = itemView.findViewById(R.id.type_of_section);
            mHasTypesSection = itemView.findViewById(R.id.has_types_section);
            mExamplesSection = itemView.findViewById(R.id.examples_section);
            mPartOfHasPartsSection = itemView.findViewById(R.id.part_of_has_parts_section);
            mPartOfSection = itemView.findViewById(R.id.part_of_section);
            mHasPartsSection = itemView.findViewById(R.id.has_parts_section);
            mInstanceOfHasInstancesSection = itemView.findViewById(R.id.instance_of_has_instances_section);
            mInstanceOfSection = itemView.findViewById(R.id.instance_of_section);
            mHasInstancesSection = itemView.findViewById(R.id.has_instances_section);
            mMemberOfHasMembersSection = itemView.findViewById(R.id.member_of_has_members_section);
            mMemberOfSection = itemView.findViewById(R.id.member_of_section);
            mHasMembersSection = itemView.findViewById(R.id.has_members_section);
            mSubstanceOfHasSubstancesSection = itemView.findViewById(R.id.substance_of_has_substances_section);
            mSubstanceOfSection = itemView.findViewById(R.id.substance_of_section);
            mHasSubstancesSection = itemView.findViewById(R.id.has_substances_section);

            mDefinitionTV = itemView.findViewById(R.id.definition_tv);
            mPartOfSpeechTV = itemView.findViewById(R.id.partofspeech_tv);
            mSynonymsTV = itemView.findViewById(R.id.synonyms_tv);
            mAntonymsTV = itemView.findViewById(R.id.antonyms_tv);
            mTypeOfTV = itemView.findViewById(R.id.type_of_tv);
            mHasTypesTV = itemView.findViewById(R.id.has_types_tv);
            mExamplesTV = itemView.findViewById(R.id.examples_tv);
            mPartOfTV = itemView.findViewById(R.id.part_of_tv);
            mHasPartsTV = itemView.findViewById(R.id.has_parts_tv);
            mInstanceOfTV = itemView.findViewById(R.id.instance_of_tv);
            mHasInstancesTV = itemView.findViewById(R.id.has_instances_tv);
            mMemberOfTV = itemView.findViewById(R.id.member_of_tv);
            mHasMembersTV = itemView.findViewById(R.id.has_members_tv);
            mSubstanceOfSection = itemView.findViewById(R.id.substance_of_section);
            mHasSubstancesSection = itemView.findViewById(R.id.has_substances_section);
        }

        public void bind(WordsUtils.WordResults info){
            if(info.definition == null){
                mDefinitionSection.setVisibility(View.GONE);
            }else{
                mDefinitionTV.setText(info.definition);
                mDefinitionSection.setVisibility(View.VISIBLE);
            }

            if(info.partOfSpeech == null){
                mPartOfSpeechSection.setVisibility(View.GONE);
            }else{
                if(info.partOfSpeech.equals("noun")){
                    mPartOfSpeechTV.setBackgroundColor(mContext.getResources().getColor(R.color.Red));
                }else if(info.partOfSpeech.equals("verb")){
                    mPartOfSpeechTV.setBackgroundColor(mContext.getResources().getColor(R.color.Orange));
                }else if(info.partOfSpeech.equals("pronoun")){
                    mPartOfSpeechTV.setBackgroundColor(mContext.getResources().getColor(R.color.Yellow));
                }else if(info.partOfSpeech.equals("adjective")){
                    mPartOfSpeechTV.setBackgroundColor(mContext.getResources().getColor(R.color.Green));
                }else if(info.partOfSpeech.equals("adverb")){
                    mPartOfSpeechTV.setBackgroundColor(mContext.getResources().getColor(R.color.LightGrey));
                }else if(info.partOfSpeech.equals("preposition")){
                    mPartOfSpeechTV.setBackgroundColor(mContext.getResources().getColor(R.color.LightSkyBlue));
                }else if(info.partOfSpeech.equals("conjunction")){
                    mPartOfSpeechTV.setBackgroundColor(mContext.getResources().getColor(R.color.MediumPurple));
                }else if(info.partOfSpeech.equals("interjection")){
                    mPartOfSpeechTV.setBackgroundColor(mContext.getResources().getColor(R.color.PeachPuff));
                }
                mPartOfSpeechTV.setText(info.partOfSpeech);
                mPartOfSpeechSection.setVisibility(View.VISIBLE);
            }

            if(info.examples == null){
                mExamplesSection.setVisibility(View.GONE);
            }else{
                String examplesText = "";
                for (String example : info.examples) {
                    examplesText = examplesText + NDASH_UNICODE + SPACES + example + NEW_LINE_UNICODE;
                }
                mExamplesTV.setText(examplesText);
                mExamplesSection.setVisibility(View.VISIBLE);
            }

            if(info.synonyms == null && info.antonyms == null){
                mSynonymsAntonymsSection.setVisibility(View.GONE);
            }else{
                if(info.synonyms == null){
                    mSynonymsSection.setVisibility(View.GONE);
                }else{
                    String synonymsText = "";
                    for (String synonym : info.synonyms) {
                        synonymsText = synonymsText + BULLETS_POINTS_UNICODE + SPACES + synonym + NEW_LINE_UNICODE;
                    }
                    mSynonymsTV.setText(synonymsText);
                    mSynonymsSection.setVisibility(View.VISIBLE);
                }

                if(info.antonyms == null){
                    mAntonymsSection.setVisibility(View.GONE);
                }else{
                    String antonymsText = "";
                    for (String antonym : info.antonyms) {
                        antonymsText = antonymsText + BULLETS_POINTS_UNICODE + SPACES + antonym + NEW_LINE_UNICODE;
                    }
                    mAntonymsTV.setText(antonymsText);
                    mAntonymsSection.setVisibility(View.VISIBLE);
                }
                mSynonymsAntonymsSection.setVisibility(View.VISIBLE);
            }

            if(info.typeOf == null && info.hasTypes == null){
                mTypeOfHasTypesSection.setVisibility(View.GONE);
            } else{
                if(info.typeOf == null){
                    mTypeOfSection.setVisibility(View.GONE);
                }else{
                    String typeOfText = "";
                    for (String typeOf : info.typeOf){
                        typeOfText = typeOfText + BULLETS_POINTS_UNICODE + SPACES + typeOf + NEW_LINE_UNICODE;
                    }
                    mTypeOfTV.setText(typeOfText);
                    mTypeOfSection.setVisibility(View.VISIBLE);
                }

                if(info.hasTypes == null){
                    mHasTypesSection.setVisibility(View.GONE);
                } else{
                    String hasTypesText = "";
                    for (String hasType : info.hasTypes) {
                        hasTypesText = hasTypesText + BULLETS_POINTS_UNICODE + SPACES + hasType + NEW_LINE_UNICODE;
                    }
                    mHasTypesTV.setText(hasTypesText);
                    mHasTypesSection.setVisibility(View.VISIBLE);
                }
                mTypeOfHasTypesSection.setVisibility(View.VISIBLE);
            }

            if(info.partOf == null && info.hasParts == null){
                mPartOfHasPartsSection.setVisibility(View.GONE);
            } else{
                if(info.partOf == null){
                    mPartOfSection.setVisibility(View.GONE);
                }else{
                    String partOfText = "";
                    for (String partOf : info.partOf){
                        partOfText = partOfText + BULLETS_POINTS_UNICODE + SPACES + partOf + NEW_LINE_UNICODE;
                    }
                    mPartOfTV.setText(partOfText);
                    mPartOfSection.setVisibility(View.VISIBLE);
                }

                if(info.hasParts == null){
                    mHasPartsSection.setVisibility(View.GONE);
                } else{
                    String hasPartsText = "";
                    for (String hasPart : info.hasParts) {
                        hasPartsText = hasPartsText + BULLETS_POINTS_UNICODE + SPACES + hasPart + NEW_LINE_UNICODE;
                    }
                    mHasPartsTV.setText(hasPartsText);
                    mHasPartsSection.setVisibility(View.VISIBLE);
                }
                mPartOfHasPartsSection.setVisibility(View.VISIBLE);
            }
        }
    }
}
