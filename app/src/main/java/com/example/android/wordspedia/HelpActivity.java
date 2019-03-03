package com.example.android.wordspedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HelpActivity extends AppCompatActivity {

    private final String BULLETS_POINTS_UNICODE = "\u2022";
    private final String NEW_LINE_UNICODE = "\n";
    private final String NDASH_UNICODE = "\u2013";
    private final String COMMAS_UNICODE = "\u002c";
    private final String SPACES = "\u0020";
    private final String TABS = "\u0009";
    private final String BOLD_TAG_BEGIN = "<b>";
    private final String BOLD_TAG_END = "</b>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        createHelper();
    }

    private void createHelper(){
        List<String> helper = new ArrayList<String>();
        String also = getResources().getString(R.string.also_header).toUpperCase() + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.also_helper) + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.also_example);
        String antonyms = getResources().getString(R.string.antonyms_header).toUpperCase() + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.antonyms_helper);
        String definition = getResources().getString(R.string.definition_header).toUpperCase() + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.definition_helper);
        String entails = getResources().getString(R.string.entails_header).toUpperCase() + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.entails_helper) + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.entails_example);
        String examples = getResources().getString(R.string.examples_header).toUpperCase() + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.examples_helper);
        String hasCategories = getResources().getString(R.string.has_categories_header).toUpperCase() + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.has_categories_helper) + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.has_categories_example);
        String hasInstances = getResources().getString(R.string.has_instances_header).toUpperCase() + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.has_instances_helper) + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.has_instances_example);
        String hasMembers = getResources().getString(R.string.has_members_header).toUpperCase() + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.has_members_helper) + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.has_members_example);
        String hasSubstances = getResources().getString(R.string.has_substances_header).toUpperCase() + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.has_substances_helper) + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.has_substances_example);
        String hasParts = getResources().getString(R.string.has_parts_header).toUpperCase() + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.has_parts_helper) + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.has_parts_example);
        String hasUsages = getResources().getString(R.string.has_usages_header).toUpperCase() + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.has_usages_helper) + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.has_usages_example);
        String hasTypes = getResources().getString(R.string.has_types_header).toUpperCase() + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.has_types_helper) + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.has_types_example);
        String inCategory = getResources().getString(R.string.in_category_header).toUpperCase() + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.in_category_helper) + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.in_category_example);
        String inRegion = getResources().getString(R.string.in_region_header).toUpperCase() + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.in_region_helper) + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.in_region_example);
        String instanceOf = getResources().getString(R.string.instance_of_header).toUpperCase() + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.instance_of_helper) + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.instance_of_example);
        String memberOf = getResources().getString(R.string.member_of_header).toUpperCase() + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.member_of_helper) + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.member_of_example);
        String partOf = getResources().getString(R.string.part_of_header).toUpperCase() + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.part_of_helper) + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.part_of_example);
        String pertainsTo = getResources().getString(R.string.pertains_to_header).toUpperCase() + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.pertains_to_helper) + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.pertains_to_example);
        String similarTo = getResources().getString(R.string.similar_to_header).toUpperCase() + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.similar_to_helper) + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.similar_to_example);
        String substanceOf = getResources().getString(R.string.substance_of_header).toUpperCase() + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.substance_of_helper) + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.substance_of_example);
        String synonyms = getResources().getString(R.string.synonyms_header).toUpperCase() + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.synonyms_helper);
        String regionOf = getResources().getString(R.string.region_of_header).toUpperCase() + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.region_of_helper) + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.region_of_example);
        String usageOf = getResources().getString(R.string.usage_of_header).toUpperCase() + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.usage_of_helper) + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.usage_of_example);
        String typeOf = getResources().getString(R.string.type_of_header).toUpperCase() + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.type_of_helper) + NEW_LINE_UNICODE
                + NDASH_UNICODE + SPACES + getResources().getString(R.string.type_of_example);



        helper.add(also);
        helper.add(antonyms);
        helper.add(definition);
        helper.add(entails);
        helper.add(examples);
        helper.add(hasCategories);
        helper.add(hasInstances);
        helper.add(hasMembers);
        helper.add(hasSubstances);
        helper.add(hasParts);
        helper.add(hasUsages);
        helper.add(hasTypes);
        helper.add(inCategory);
        helper.add(inRegion);
        helper.add(instanceOf);
        helper.add(memberOf);
        helper.add(partOf);
        helper.add(pertainsTo);
        helper.add(similarTo);
        helper.add(substanceOf);
        helper.add(synonyms);
        helper.add(regionOf);
        helper.add(usageOf);
        helper.add(typeOf);

        ListView helperListView = (ListView)findViewById(R.id.helper_list_view);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, helper);
        helperListView.setAdapter(arrayAdapter);
    }
}
