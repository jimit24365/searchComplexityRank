package com.jimit24365.searchcomplexity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jimit24365.searchcomplexity.models.SearchItem;
import com.jimit24365.searchcomplexity.models.SearchResponse;
import com.jimit24365.searchcomplexity.rest.SearchApiClient;
import com.jimit24365.searchcomplexity.rest.SearchApiInterface;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView searchBoxTv;
    Button searchBtn;
    RecyclerView listView;
    AVLoadingIndicatorView loadingIndicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchBoxTv = (TextView) findViewById(R.id.search_edt);
        searchBtn = (Button) findViewById(R.id.search_btn);
        listView = (RecyclerView) findViewById(R.id.search_list);
        loadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.loading_indicator);
        listView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final String apiKey = getResources().getString(R.string.api_key);
        final String cxKey = getResources().getString(R.string.cx_key);
        setSupportActionBar(toolbar);

        final SearchApiInterface apiService =
                SearchApiClient.getClient(MainActivity.this).create(SearchApiInterface.class);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = searchBoxTv.getText().toString();
                Call<SearchResponse> searchResultCall = apiService.searchInternet(cxKey, apiKey, searchQuery);
                loadingIndicatorView.setVisibility(View.VISIBLE);

                searchResultCall.enqueue(new Callback<SearchResponse>() {

                    @Override
                    public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                        if (response != null) {
                            SearchResponse searchResponse = response.body();
                            if (searchResponse != null) {
                                ArrayList<SearchItem> searchItems = searchResponse.getItems();
                                if (searchItems != null && !searchItems.isEmpty()) {
                                    SearchResultListAdapter searchResultListAdapter = new SearchResultListAdapter(MainActivity.this, searchItems);
                                    listView.setAdapter(searchResultListAdapter);
                                }
                            }
                        }
                        loadingIndicatorView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<SearchResponse> call, Throwable t) {
                        loadingIndicatorView.setVisibility(View.GONE);
                    }
                });

            }
        });
    }

}
