package com.jimit24365.searchcomplexity.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by jimit24365 on 5/4/17.
 */

public class SearchResponse {

    @SerializedName("items")
    ArrayList<SearchItem> items;

    public ArrayList<SearchItem> getItems() {
        return items;
    }
}
