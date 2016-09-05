/*
 * Copyright 2016. Carlos Ottoboni
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.ottoboni.hqtracker.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.ottoboni.hqtracker.R;
import br.com.ottoboni.hqtracker.controllers.DatabaseController;
import br.com.ottoboni.hqtracker.model.Collection;
import br.com.ottoboni.hqtracker.model.enums.CollectionStatus;
import br.com.ottoboni.hqtracker.util.FontUtil;

public class CollectionAdapter extends RecyclerView.Adapter <CollectionAdapter.ViewHolder> {

    private static final String COUNT_SEPARATOR = "/";
    private List<Collection> mItems;
    private ViewHolder.CollectionItemClickListener mItemClickListener;

    public CollectionAdapter(List<Collection> items, ViewHolder.CollectionItemClickListener listener) {
        mItems = items;
        mItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collection,
            parent, false);

        ViewHolder viewHolder = new ViewHolder(rootView, mItemClickListener);

        setTypeface(viewHolder);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mCollectionName.setText(mItems.get(position).getCollectionName());

        CollectionStatus status = mItems.get(position).getStatus();
        holder.mCollectionStatus.setText(CollectionStatus.getStatusText(status));

        String collectionId = mItems.get(position).getCollectionId();

        long totalComicBooks = DatabaseController.getInstance().countComicBooks(collectionId);
        long acquiredComicBooks = DatabaseController.getInstance().countAcquiredComicBooks(collectionId);

        String countItems = acquiredComicBooks + COUNT_SEPARATOR + totalComicBooks;

        holder.mCountItems.setText(countItems);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    private void setTypeface(ViewHolder viewHolder) {

        viewHolder.mCollectionName.setTypeface(FontUtil.ROBOTO_MEDIUM);
        viewHolder.mCollectionStatus.setTypeface(FontUtil.ROBOTO_REGULAR);
        viewHolder.mCountItems.setTypeface(FontUtil.ROBOTO_REGULAR);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mCollectionName;
        private TextView mCollectionStatus;
        private TextView mCountItems;
        private CollectionItemClickListener mListener;

        public ViewHolder(View itemView, CollectionItemClickListener listener) {
            super(itemView);
            mListener = listener;
            mCollectionName = (TextView) itemView.findViewById(R.id.collection_name);
            mCollectionStatus = (TextView) itemView.findViewById(R.id.collection_status);
            mCountItems = (TextView) itemView.findViewById(R.id.count_items);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            mListener.onItemClick(v);
        }

        public interface CollectionItemClickListener {
            void onItemClick(View view);
        }
    }
}
