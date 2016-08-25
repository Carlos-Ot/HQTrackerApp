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

package br.com.ottoboni.hqtracker.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Gallery;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.ottoboni.hqtracker.R;
import br.com.ottoboni.hqtracker.app.App;
import br.com.ottoboni.hqtracker.controllers.DatabaseController;
import br.com.ottoboni.hqtracker.model.Collection;
import br.com.ottoboni.hqtracker.ui.adapter.CollectionAdapter;

public class CollectionActivity extends AppCompatActivity implements CollectionAdapter.ViewHolder.CollectionItemClickListener{

    private RecyclerView collectionList;

    private List<Collection> mItems;
    private CollectionAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        initViews();
        loadList();
    }

    private void initViews() {
        collectionList = (RecyclerView) findViewById(R.id.collection_list);

    }

    private void loadList() {

        mItems = DatabaseController.getInstance().getUntrackedCollections();
        mAdapter = new CollectionAdapter(mItems, this);

        collectionList.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(App.getContext());

        collectionList.setLayoutManager(mLayoutManager);

        collectionList.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(View view) {
        int position = collectionList.getChildAdapterPosition(view);
        String collectionId = mItems.get(position).getCollectionId();

        Intent intent = new Intent(CollectionActivity.this, ComicBookActivity.class);
        intent.putExtra(ComicBookActivity.KEY_COLLECTION_ID, collectionId);
        startActivity(intent);
    }
}
