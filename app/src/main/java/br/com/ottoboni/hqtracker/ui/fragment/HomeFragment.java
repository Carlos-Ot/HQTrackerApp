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
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.ottoboni.hqtracker.R;
import br.com.ottoboni.hqtracker.app.App;
import br.com.ottoboni.hqtracker.controllers.DatabaseController;
import br.com.ottoboni.hqtracker.model.ComicBook;
import br.com.ottoboni.hqtracker.ui.adapter.ComicBookAdapter;
import br.com.ottoboni.hqtracker.ui.decorator.ListDecorator;

public class HomeFragment extends Fragment {

    private ComicBookAdapter comicBookAdapter;
    private List<ComicBook> comicBookList;

    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView collectionList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
        savedInstanceState) {

        Log.d("ottoboni", "PAssou no onCreateView do HomeFragment");

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        initView(rootView);
        loadListItems();

        return rootView;
    }

    private void initView(View rootView) {

        collectionList = (RecyclerView) rootView.findViewById(R.id.collection_list);
    }

    private void loadListItems() {

        comicBookList =  DatabaseController.getInstance().getPendingComicBooks();
        comicBookAdapter = new ComicBookAdapter(comicBookList);

        collectionList.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        collectionList.setLayoutManager(mLayoutManager);


        collectionList.setAdapter(comicBookAdapter);
    }
}
