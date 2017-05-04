package com.jimit24365.searchcomplexity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jimit24365.searchcomplexity.models.SearchItem;

import java.util.ArrayList;

/**
 * Created by jimit24365 on 5/4/17.
 */

public class SearchResultListAdapter extends RecyclerView.Adapter<SearchResultListAdapter.MyViewHolder> {
    Context mContext;
    ArrayList<SearchItem> searchResultList;

    public SearchResultListAdapter(Context context, ArrayList<SearchItem> searchItems) {
        this.mContext = context;
        this.searchResultList = searchItems;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View groupView = inflater.inflate(R.layout.search_item_layout, null);
        MyViewHolder vh = new MyViewHolder(groupView);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SearchItem currentSearchItem = searchResultList.get(position);
        if (currentSearchItem != null) {
            holder.populateSearchResult(currentSearchItem);
        }
    }

    @Override
    public int getItemCount() {
        return searchResultList != null && !searchResultList.isEmpty() ? searchResultList.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTv;
        private TextView linkTv;
        private TextView scoreTv;
        private TextView descriptionTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.title_tv);
            linkTv = (TextView) itemView.findViewById(R.id.link_tv);
            scoreTv = (TextView) itemView.findViewById(R.id.score_tv);
            descriptionTv = (TextView) itemView.findViewById(R.id.description);
        }

        public void populateSearchResult(SearchItem searchItem) {
            if (searchItem != null) {
                String title = searchItem.getTitle();
                String link = searchItem.getDisplayLink();
                String score = String.valueOf(searchItem.getComplexityScore());
                String description = String.valueOf(searchItem.getDescription());
                if (!TextUtils.isEmpty(title)) {
                    titleTv.setText(title);
                }
                if (!TextUtils.isEmpty(link)) {
                    linkTv.setText(link);
                }
                if (!TextUtils.isEmpty(score)) {
                    scoreTv.setText(score);
                }
                if (!TextUtils.isEmpty(description)) {
                    descriptionTv.setText(description);
                }
            }
        }

    }
}
