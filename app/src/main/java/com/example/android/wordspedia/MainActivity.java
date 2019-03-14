package com.example.android.wordspedia;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ShareCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.v7.widget.SearchView;

import com.example.android.wordspedia.data.WordPreference;
import com.example.android.wordspedia.utils.WordsUtils;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String VIEWMODEL_KEY = "WordViewModel";
    private static final String ACTIVITY_SOURCE_OF_CONTENT = "WORDSAPI";

    private RecyclerView mWordInfosRV;
    private WordInfosAdapter mWordInfosAdapter;
    private TextView mLoadingErrorTV;
    private TextView mWordTV;
    private ProgressBar mLoadingIndicatorPB;
    private DrawerLayout mDrawerLayout;
    private WordViewModel mWordInfosViewModel;

    private WordsUtils.WordSearchResults mWordSearchResults;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWordInfosRV = findViewById(R.id.rv_word_infos);
        mWordTV = findViewById(R.id.word_tv);
        mLoadingErrorTV = findViewById(R.id.tv_loading_error);
        mLoadingIndicatorPB = findViewById(R.id.pb_loading);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nv_nav_drawer);
        navigationView.setNavigationItemSelectedListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_nav_menu);

        mWordInfosRV.setLayoutManager(new LinearLayoutManager(this));
        mWordInfosRV.setHasFixedSize(false);

        mWordInfosAdapter = new WordInfosAdapter(this);
        mWordInfosRV.setAdapter(mWordInfosAdapter);

        mWordInfosViewModel = ViewModelProviders.of(this, new ViewModelFactory(mLoadingIndicatorPB, getURL(), ACTIVITY_SOURCE_OF_CONTENT)).get(WordViewModel.class);
        mWordInfosViewModel.getSearchResults().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String searchResultsJSON) {
                mLoadingIndicatorPB.setVisibility(View.INVISIBLE);
                if (searchResultsJSON != null) {
                    mLoadingErrorTV.setVisibility(View.INVISIBLE);
                    mWordInfosRV.setVisibility(View.VISIBLE);
                    mWordSearchResults = WordsUtils.parseWordSearchResults(searchResultsJSON);
                    WordPreference.setWord(mWordSearchResults.word);
                    mWordTV.setText(mWordSearchResults.word);
                    mWordInfosAdapter.updateWordInfos(mWordSearchResults.results);
                } else {
                    mLoadingErrorTV.setVisibility(View.VISIBLE);
                    mWordInfosRV.setVisibility(View.INVISIBLE);
                }
                mLoadingIndicatorPB.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_options, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.option_search).getActionView();
        searchView.setQueryHint(getResources().getString(R.string.option_search_hint));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                mWordInfosViewModel.updateURL(getURL(s));
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(Gravity.START);
                return true;
            case R.id.option_random:
                mWordInfosViewModel.updateURLRandom(getURL());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        mDrawerLayout.closeDrawers();
        switch (menuItem.getItemId()) {
            case R.id.nav_helper:
                Intent helpIntent = new Intent(this, HelpActivity.class);
                startActivity(helpIntent);
                return true;
            case R.id.nav_images:
                Intent imagesIntent = new Intent(this, WordImagesActivity.class);
                startActivity(imagesIntent);
                return true;
            case R.id.nav_frequency:
                Intent ratingIntent = new Intent(this, WordRatingActivity.class);
                startActivity(ratingIntent);
                return true;
            case R.id.nav_rhymes:
                Intent rhymesIntent = new Intent(this, WordRhymesActivity.class);
                startActivity(rhymesIntent);
                return true;
            case R.id.nav_pronunciation:
                Intent pronunciationIntent = new Intent(this, WordPronunciationActivity.class);
                startActivity(pronunciationIntent);
                return true;
            case R.id.nav_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }

    public String getURL(){
        String wordsAPIRequestURL = WordsUtils.buildRandomWordSearchURL();
        Log.d(TAG, wordsAPIRequestURL);
        return wordsAPIRequestURL;
    }

    public String getURL(String word) {
        String wordsAPIRequestURL = WordsUtils.buildWordSearchURL(word);
        Log.d(TAG, wordsAPIRequestURL);
        return wordsAPIRequestURL;
    }



    /*private static final String TAG = MainActivity.class.getSimpleName();
    private static final String REPOS_ARRAY_KEY = "githubRepos";
    private static final String SEARCH_URL_KEY = "githubSearchURL";

    private static final int GITHUB_SEARCH_LOADER_ID = 0;

    private RecyclerView mSearchResultsRV;
    private EditText mSearchBoxET;
    private TextView mLoadingErrorTV;
    private ProgressBar mLoadingPB;
    private DrawerLayout mDrawerLayout;

    private GitHubSearchAdapter mGitHubSearchAdapter;
    private ArrayList<GitHubRepo> mRepos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchBoxET = findViewById(R.id.et_search_box);
        mSearchResultsRV = findViewById(R.id.rv_search_results);
        mLoadingErrorTV = findViewById(R.id.tv_loading_error);
        mLoadingPB = findViewById(R.id.pb_loading);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nv_nav_drawer);
        navigationView.setNavigationItemSelectedListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_nav_menu);

        mSearchResultsRV.setLayoutManager(new LinearLayoutManager(this));
        mSearchResultsRV.setHasFixedSize(true);

        mGitHubSearchAdapter = new GitHubSearchAdapter(this);
        mSearchResultsRV.setAdapter(mGitHubSearchAdapter);

        if (savedInstanceState != null && savedInstanceState.containsKey(REPOS_ARRAY_KEY)) {
            mRepos = (ArrayList<GitHubRepo>) savedInstanceState.getSerializable(REPOS_ARRAY_KEY);
            mGitHubSearchAdapter.updateSearchResults(mRepos);
        }

        getSupportLoaderManager().initLoader(GITHUB_SEARCH_LOADER_ID, null, this);

        Button searchButton = findViewById(R.id.btn_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = mSearchBoxET.getText().toString();
                if (!TextUtils.isEmpty(searchQuery)) {
                    doGitHubSearch(searchQuery);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(Gravity.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void doGitHubSearch(String query) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String sort = preferences.getString(getString(R.string.pref_sort_key),
                getString(R.string.pref_sort_default));
        String language = preferences.getString(getString(R.string.pref_language_key),
                getString(R.string.pref_language_default));
        String user = preferences.getString(getString(R.string.pref_user_key),"");
        boolean searchInName = preferences.getBoolean(getString(R.string.pref_in_name_key), true);
        boolean searchInDescription = preferences.getBoolean(getString(R.string.pref_in_description_key), true);
        boolean searchInReadme = preferences.getBoolean(getString(R.string.pref_in_readme_key), false);

        String url = GitHubUtils.buildGitHubSearchURL(query, sort, language, user, searchInName,
                searchInDescription, searchInReadme);
        Log.d(TAG, "querying search URL: " + url);

        Bundle args = new Bundle();
        args.putString(SEARCH_URL_KEY, url);
        mLoadingPB.setVisibility(View.VISIBLE);
        getSupportLoaderManager().restartLoader(GITHUB_SEARCH_LOADER_ID, args, this);
    }

    @Override
    public void onSearchItemClick(GitHubRepo repo) {
        Intent intent = new Intent(this, RepoDetailActivity.class);
        intent.putExtra(GitHubUtils.EXTRA_GITHUB_REPO, repo);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mRepos != null) {
            outState.putSerializable(REPOS_ARRAY_KEY, mRepos);
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        String url = null;
        if (bundle != null) {
            url = bundle.getString(SEARCH_URL_KEY);
        }
        return new GitHubSearchLoader(this, url);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        Log.d(TAG, "loader finished loading");
        if (s != null) {
            mLoadingErrorTV.setVisibility(View.INVISIBLE);
            mSearchResultsRV.setVisibility(View.VISIBLE);
            mRepos = GitHubUtils.parseGitHubSearchResults(s);
            mGitHubSearchAdapter.updateSearchResults(mRepos);
        } else {
            mLoadingErrorTV.setVisibility(View.VISIBLE);
            mSearchResultsRV.setVisibility(View.INVISIBLE);
        }
        mLoadingPB.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        // Nothing to do here...
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        mDrawerLayout.closeDrawers();
        switch (menuItem.getItemId()) {
            case R.id.nav_search:
                return true;
            case R.id.nav_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            case R.id.nav_saved_repos:
                Intent savedReposIntent = new Intent(this, SavedReposActivity.class);
                startActivity(savedReposIntent);
                return true;
            default:
                return false;
        }
    }*/
}