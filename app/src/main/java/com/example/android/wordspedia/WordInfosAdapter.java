package com.example.android.wordspedia;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Space;
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

        private FrameLayout mInfoItem;
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
        private LinearLayout mUsageOfHasUsagesSection;
        private LinearLayout mUsageOfSection;
        private LinearLayout mHasUsagesSection;
        private LinearLayout mInCategoryHasCategoriesSection;
        private LinearLayout mInCategorySection;
        private LinearLayout mHasCategoriesSection;
        private LinearLayout mInRegionRegionOfSection;
        private LinearLayout mInRegionSection;
        private LinearLayout mRegionOfSection;
        private LinearLayout mPertainsToSection;
        private LinearLayout mSimilarToSection;
        private LinearLayout mEntailsAlsoSection;
        private LinearLayout mEntailsSection;
        private LinearLayout mAlsoSection;

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
        private TextView mUsageOfTV;
        private TextView mHasUsagesTV;
        private TextView mInCategoryTV;
        private TextView mHasCategoriesTV;
        private TextView mInRegionTV;
        private TextView mRegionOfTV;
        private TextView mPertainsToTV;
        private TextView mSimilarToTV;
        private TextView mEntailsTV;
        private TextView mAlsoTV;

        private final String BULLETS_POINTS_UNICODE = "\u2022";
        private final String NEW_LINE_UNICODE = "\n";
        private final String NDASH_UNICODE = "\u2013";
        private final String COMMAS_UNICODE = "\u002c";
        private final String SPACES = "\u0020";

        public WordInfoViewHolder(View itemView){
            super(itemView);
            mInfoItem = itemView.findViewById(R.id.info_item);
            mDefinitionSection = itemView.findViewById(R.id.definition_section);
            mPartOfSpeechSection = itemView.findViewById(R.id.part_of_speech_section);
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
            mUsageOfHasUsagesSection = itemView.findViewById(R.id.usage_of_has_usages_section);
            mUsageOfSection = itemView.findViewById(R.id.usage_of_section);
            mHasUsagesSection = itemView.findViewById(R.id.has_usages_section);
            mInCategoryHasCategoriesSection = itemView.findViewById(R.id.in_category_has_categories_section);
            mInCategorySection = itemView.findViewById(R.id.in_category_section);
            mHasCategoriesSection = itemView.findViewById(R.id.has_categories_section);
            mInRegionRegionOfSection = itemView.findViewById(R.id.in_region_region_of_section);
            mInRegionSection = itemView.findViewById(R.id.in_region_section);
            mRegionOfSection = itemView.findViewById(R.id.region_of_section);
            mPertainsToSection = itemView.findViewById(R.id.pertains_to_section);
            mSimilarToSection = itemView.findViewById(R.id.similar_to_section);
            mEntailsAlsoSection = itemView.findViewById(R.id.entails_also_section);
            mEntailsSection = itemView.findViewById(R.id.entails_section);
            mAlsoSection = itemView.findViewById(R.id.also_section);

            mDefinitionTV = itemView.findViewById(R.id.definition_tv);
            mPartOfSpeechTV = itemView.findViewById(R.id.part_of_speech_tv);
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
            mSubstanceOfTV = itemView.findViewById(R.id.substance_of_tv);
            mHasSubstancesTV = itemView.findViewById(R.id.has_substances_tv);
            mUsageOfTV = itemView.findViewById(R.id.usage_of_tv);
            mHasUsagesTV = itemView.findViewById(R.id.has_usages_tv);
            mInCategoryTV = itemView.findViewById(R.id.in_category_tv);
            mHasCategoriesTV = itemView.findViewById(R.id.has_categories_tv);
            mInRegionTV = itemView.findViewById(R.id.in_region_tv);
            mRegionOfTV = itemView.findViewById(R.id.region_of_tv);
            mPertainsToTV = itemView.findViewById(R.id.pertains_to_tv);
            mSimilarToTV = itemView.findViewById(R.id.similar_to_tv);
            mEntailsTV = itemView.findViewById(R.id.entails_tv);
            mAlsoTV = itemView.findViewById(R.id.also_tv);
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

            if(info.pertainsTo == null){
                mPertainsToSection.setVisibility(View.GONE);
            } else{
                String pertainsToText = "";
                for (String pertainTo : info.pertainsTo) {
                    if(info.pertainsTo.get(info.pertainsTo.size()-1) == pertainTo){
                        pertainsToText = pertainsToText + pertainTo;
                    }else{
                        pertainsToText = pertainsToText + pertainTo + COMMAS_UNICODE + SPACES;
                    }
                }
                mPertainsToTV.setText(pertainsToText);
                mPertainsToSection.setVisibility(View.VISIBLE);
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

            if(info.similarTo == null){
                mSimilarToSection.setVisibility(View.GONE);
            } else{
                String similarToText = "" + NDASH_UNICODE + SPACES;
                for (String similarTo : info.similarTo) {
                    if(info.similarTo.get(info.similarTo.size()-1) == similarTo){
                        similarToText = similarToText + similarTo;
                    }else{
                        similarToText = similarToText + similarTo + COMMAS_UNICODE + SPACES;
                    }
                }
                mSimilarToTV.setText(similarToText);
                mSimilarToSection.setVisibility(View.VISIBLE);
            }

            if(info.entails == null && info.also == null){
                mEntailsAlsoSection.setVisibility(View.GONE);
            } else{
                if(info.entails == null){
                    mEntailsSection.setVisibility(View.GONE);
                }else{
                    String entailsText = "";
                    for (String entail : info.entails) {
                        entailsText = entailsText + BULLETS_POINTS_UNICODE + SPACES + entail + NEW_LINE_UNICODE;
                    }
                    mEntailsTV.setText(entailsText);
                    mEntailsSection.setVisibility(View.VISIBLE);
                }

                if(info.also == null){
                    mAlsoSection.setVisibility(View.GONE);
                }else{
                    String alsoText = "";
                    for (String also : info.also) {
                        alsoText = alsoText + BULLETS_POINTS_UNICODE + SPACES + also + NEW_LINE_UNICODE;
                    }
                    mAlsoTV.setText(alsoText);
                    mAlsoSection.setVisibility(View.VISIBLE);
                }
                mEntailsAlsoSection.setVisibility(View.VISIBLE);
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

            if(info.memberOf == null && info.hasMembers == null){
                mMemberOfHasMembersSection.setVisibility(View.GONE);
            } else{
                if(info.memberOf == null){
                    mMemberOfSection.setVisibility(View.GONE);
                }else{
                    String memberOfText = "";
                    for (String memberOf : info.memberOf) {
                        memberOfText = memberOfText + BULLETS_POINTS_UNICODE + SPACES + memberOf + NEW_LINE_UNICODE;
                    }
                    mMemberOfTV.setText(memberOfText);
                    mMemberOfSection.setVisibility(View.VISIBLE);
                }

                if(info.hasMembers == null){
                    mHasMembersSection.setVisibility(View.GONE);
                } else{
                    String hasMembersText = "";
                    for (String hasMember : info.hasMembers) {
                        hasMembersText = hasMembersText + BULLETS_POINTS_UNICODE + SPACES + hasMember + NEW_LINE_UNICODE;
                    }
                    mHasMembersTV.setText(hasMembersText);
                    mHasMembersSection.setVisibility(View.VISIBLE);
                }
                mMemberOfHasMembersSection.setVisibility(View.VISIBLE);
            }

            if(info.usageOf == null && info.hasUsages == null){
                mUsageOfHasUsagesSection.setVisibility(View.GONE);
            } else{
                if(info.usageOf == null){
                    mUsageOfSection.setVisibility(View.GONE);
                }else{
                    String usageOfText = "";
                    for (String usageOf : info.usageOf) {
                        usageOfText = usageOfText + BULLETS_POINTS_UNICODE + SPACES + usageOf + NEW_LINE_UNICODE;
                    }
                    mUsageOfTV.setText(usageOfText);
                    mUsageOfSection.setVisibility(View.VISIBLE);
                }

                if(info.hasUsages == null){
                    mHasUsagesSection.setVisibility(View.GONE);
                }else{
                    String hasUsagesText = "";
                    for (String hasUsage : info.hasUsages) {
                        hasUsagesText = hasUsagesText + BULLETS_POINTS_UNICODE + SPACES + hasUsage + NEW_LINE_UNICODE;
                    }
                    mHasUsagesTV.setText(hasUsagesText);
                    mHasUsagesSection.setVisibility(View.VISIBLE);
                }
                mUsageOfHasUsagesSection.setVisibility(View.VISIBLE);
            }

            if(info.instanceOf == null && info.hasInstances == null){
                mInstanceOfHasInstancesSection.setVisibility(View.GONE);
            } else{
                if(info.instanceOf == null){
                    mInstanceOfSection.setVisibility(View.GONE);
                }else{
                    String instanceOfText = "";
                    for (String instanceOf : info.instanceOf) {
                        instanceOfText = instanceOfText + BULLETS_POINTS_UNICODE + SPACES + instanceOf + NEW_LINE_UNICODE;
                    }
                    mInstanceOfTV.setText(instanceOfText);
                    mInstanceOfSection.setVisibility(View.VISIBLE);
                }

                if(info.hasInstances == null){
                    mHasInstancesSection.setVisibility(View.GONE);
                }else{
                    String hasInstancesText = "";
                    for (String hasInstance : info.hasInstances) {
                        hasInstancesText = hasInstancesText + BULLETS_POINTS_UNICODE + SPACES + hasInstance + NEW_LINE_UNICODE;
                    }
                    mHasInstancesTV.setText(hasInstancesText);
                    mHasInstancesSection.setVisibility(View.VISIBLE);
                }
                mInstanceOfHasInstancesSection.setVisibility(View.VISIBLE);
            }

            if(info.substanceOf == null && info.hasSubstances == null){
                mSubstanceOfHasSubstancesSection.setVisibility(View.GONE);
            } else{
                if(info.substanceOf == null){
                    mSubstanceOfSection.setVisibility(View.GONE);
                }else{
                    String substanceOfText = "";
                    for (String substanceOf : info.substanceOf) {
                        substanceOfText = substanceOfText + BULLETS_POINTS_UNICODE + SPACES + substanceOf + NEW_LINE_UNICODE;
                    }
                    mSubstanceOfTV.setText(substanceOfText);
                    mSynonymsSection.setVisibility(View.VISIBLE);
                }

                if(info.hasSubstances == null){
                    mHasSubstancesSection.setVisibility(View.GONE);
                }else{
                    String hasSubstancesText = "";
                    for (String hasSubstance : info.hasSubstances) {
                        hasSubstancesText = hasSubstancesText + BULLETS_POINTS_UNICODE + SPACES + hasSubstance + NEW_LINE_UNICODE;
                    }
                    mHasSubstancesTV.setText(hasSubstancesText);
                    mHasSubstancesSection.setVisibility(View.VISIBLE);
                }
                mSubstanceOfHasSubstancesSection.setVisibility(View.VISIBLE);
            }

            if(info.inCategory == null && info.hasCategories == null){
                mInCategoryHasCategoriesSection.setVisibility(View.GONE);
            } else{
                if(info.inCategory == null){
                    mInCategorySection.setVisibility(View.GONE);
                }else{
                    String inCategoryText = "";
                    for (String inCategory : info.inCategory) {
                        inCategoryText = inCategoryText + BULLETS_POINTS_UNICODE + SPACES + inCategory + NEW_LINE_UNICODE;
                    }
                    mInCategoryTV.setText(inCategoryText);
                    mInCategorySection.setVisibility(View.VISIBLE);
                }

                if(info.hasCategories == null){
                    mHasCategoriesSection.setVisibility(View.GONE);
                }else{
                    String hasCategoriesText = "";
                    for (String hasCategory : info.hasCategories) {
                        hasCategoriesText = hasCategoriesText + BULLETS_POINTS_UNICODE + SPACES + hasCategory + NEW_LINE_UNICODE;
                    }
                    mHasCategoriesTV.setText(hasCategoriesText);
                    mHasCategoriesSection.setVisibility(View.VISIBLE);
                }
                mInCategoryHasCategoriesSection.setVisibility(View.VISIBLE);
            }

            if(info.inRegion == null && info.regionOf == null){
                mInRegionRegionOfSection.setVisibility(View.GONE);
            } else{
                if(info.inRegion == null){
                    mInRegionSection.setVisibility(View.GONE);
                }else{
                    String inRegionText = "";
                    for (String inRegion : info.inRegion) {
                        inRegionText = inRegionText + BULLETS_POINTS_UNICODE + SPACES + inRegion + NEW_LINE_UNICODE;
                    }
                    mInRegionTV.setText(inRegionText);
                    mInRegionSection.setVisibility(View.VISIBLE);
                }

                if(info.regionOf == null){
                    mRegionOfSection.setVisibility(View.GONE);
                }else{
                    String regionOfText = "";
                    for (String regionOf : info.regionOf) {
                        regionOfText = regionOfText + BULLETS_POINTS_UNICODE + SPACES + regionOf + NEW_LINE_UNICODE;
                    }
                    mRegionOfTV.setText(regionOfText);
                    mRegionOfSection.setVisibility(View.VISIBLE);
                }
                mInRegionRegionOfSection.setVisibility(View.VISIBLE);
            }

        }
    }
}
