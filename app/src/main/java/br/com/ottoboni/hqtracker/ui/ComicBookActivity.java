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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.ottoboni.hqtracker.R;
import br.com.ottoboni.hqtracker.app.App;
import br.com.ottoboni.hqtracker.controllers.DatabaseController;
import br.com.ottoboni.hqtracker.model.ComicBook;
import br.com.ottoboni.hqtracker.ui.adapter.ComicBookAdapter;

public class ComicBookActivity extends AppCompatActivity {

    public static final String KEY_COLLECTION_ID = "collection_id";

    private RecyclerView comicBookList;

    private String mCollectionId;
    private List<ComicBook> mItems;
    private ComicBookAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_book);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mCollectionId = extras.getString(KEY_COLLECTION_ID);
        }

        comicBookList = (RecyclerView) findViewById(R.id.comic_book_list);

        mItems = DatabaseController.getInstance().getPendingComicBooks(mCollectionId);
        mAdapter = new ComicBookAdapter(mItems);

        comicBookList.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(App.getContext());

        comicBookList.setLayoutManager(mLayoutManager);

        comicBookList.setAdapter(mAdapter);

    }
}
