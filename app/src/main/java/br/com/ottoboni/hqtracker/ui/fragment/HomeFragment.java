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

package br.com.ottoboni.hqtracker.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.ottoboni.hqtracker.R;
import br.com.ottoboni.hqtracker.app.App;
import br.com.ottoboni.hqtracker.controllers.DatabaseController;
import br.com.ottoboni.hqtracker.model.Collection;
import br.com.ottoboni.hqtracker.ui.adapter.CollectionAdapter;
import br.com.ottoboni.hqtracker.util.FontUtil;

public class HomeFragment extends Fragment implements CollectionAdapter.ViewHolder.CollectionItemClickListener{

    private TextView mEmptyView;

    private CollectionAdapter mAdapter;
    private List<Collection> mItems;

    private RecyclerView collectionList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
        savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        initView(rootView);
        loadListItems();

        return rootView;
    }

    private void initView(View rootView) {

        collectionList = (RecyclerView) rootView.findViewById(R.id.collection_list);
        mEmptyView = (TextView) rootView.findViewById(R.id.empty_list);

        mEmptyView.setTypeface(FontUtil.ROBOTO_REGULAR);
    }

    private void loadListItems() {

        mItems = DatabaseController.getInstance().getTrackingCollections();

        if (!mItems.isEmpty()) {

            mEmptyView.setVisibility(View.GONE);
            collectionList.setVisibility(View.VISIBLE);

            mAdapter = new CollectionAdapter(mItems, this);

            collectionList.setHasFixedSize(true);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());

            collectionList.setLayoutManager(mLayoutManager);
            collectionList.setAdapter(mAdapter);
        }
        else {
            mEmptyView.setVisibility(View.VISIBLE);
            collectionList.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(View view) {
        Toast.makeText(App.getContext(), "clicou", Toast.LENGTH_SHORT).show();
    }
}
