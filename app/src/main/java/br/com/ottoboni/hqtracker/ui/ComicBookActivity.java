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

import android.content.Context;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bignerdranch.android.multiselector.MultiSelector;

import java.util.ArrayList;
import java.util.List;

import br.com.ottoboni.hqtracker.R;
import br.com.ottoboni.hqtracker.app.App;
import br.com.ottoboni.hqtracker.controllers.DatabaseController;
import br.com.ottoboni.hqtracker.model.Collection;
import br.com.ottoboni.hqtracker.model.ComicBook;
import br.com.ottoboni.hqtracker.model.enums.CollectionStatus;
import br.com.ottoboni.hqtracker.model.enums.ComicBookStatus;
import br.com.ottoboni.hqtracker.ui.adapter.ComicBookAdapter;
import br.com.ottoboni.hqtracker.ui.view.ItemDivider;

public class ComicBookActivity extends AppCompatActivity implements ComicBookAdapter.ViewHolder.ComicBookListener{

    public static final String KEY_COLLECTION_ID = "collection_id";
    private static final int VIBRATE_TIME = 100;

    private RecyclerView comicBookList;

    private String mCollectionId;
    private List<ComicBook> mItems;
    private List<ComicBook> mSelectedItems;
    private ComicBookAdapter mAdapter;

    private MultiSelector mMultiSelector;
    private ActionMode mActionMode = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_book);

        setupActionBar();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mCollectionId = extras.getString(KEY_COLLECTION_ID);
        }

        initView();

    }

    private void initView() {

        comicBookList = (RecyclerView) findViewById(R.id.comic_book_list);

        mItems = DatabaseController.getInstance().getComicBooks(mCollectionId);
        mMultiSelector = new MultiSelector();
        mAdapter = new ComicBookAdapter(mItems, mMultiSelector, this);

        comicBookList.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(App.getContext());

        comicBookList.setLayoutManager(mLayoutManager);
        comicBookList.addItemDecoration(new ItemDivider(this));

        comicBookList.setAdapter(mAdapter);
    }

    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onItemClick(ComicBookAdapter.ViewHolder view, MultiSelector multiSelector) {

        if (!multiSelector.tapSelection(view)) {
            Log.d("ottoboni", "Clicou de leve");
        } else {
            boolean selected = multiSelector.isSelected(view.getAdapterPosition(), view.getItemId());

            if (selected) {
                mSelectedItems.add(mItems.get(view.getAdapterPosition()));
            }
            else {
                mSelectedItems.remove(mItems.get(view.getAdapterPosition()));
                if (mSelectedItems.isEmpty()) {
                    multiSelector.setSelectable(false);
                    if (mActionMode != null) {
                        mActionMode.finish();
                    }
                }
            }
        }

    }

    @Override
    public void onItemLongClick(ComicBookAdapter.ViewHolder view, MultiSelector multiSelector) {

        if (!multiSelector.isSelectable()) {

            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_TIME);

            startSupportActionMode(mSelectMode);
            multiSelector.setSelected(view, true);

            mSelectedItems = new ArrayList<>();

            mSelectedItems.add(mItems.get(view.getAdapterPosition()));
        } else {
            multiSelector.setSelected(view, true);
            mSelectedItems.add(mItems.get(view.getAdapterPosition()));
        }
    }

    private ActionMode.Callback mSelectMode = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.comic_book_list_item, menu);
            mMultiSelector.setSelectable(true);
            mActionMode = mode;
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.status_aquired:
                    updateComicBookStatus(ComicBookStatus.acquired);
                    mode.finish();
                    return true;
                case R.id.status_unavailable:
                    updateComicBookStatus(ComicBookStatus.unavailable);
                    mode.finish();
                    return true;
                case R.id.status_missing:
                    updateComicBookStatus(ComicBookStatus.missing);
                    mode.finish();
                    return true;
                default:
                    break;
            }

            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
           clearSelection();
        }
    };

    private void updateComicBookStatus(ComicBookStatus status) {
        for (ComicBook comicBook : mSelectedItems) {
            comicBook.setStatus(status);
            DatabaseController.getInstance().updateComicBook(comicBook);
        }

        Collection collection = DatabaseController.getInstance().getCollection(mCollectionId);
        collection.setStatus(CollectionStatus.tracking);

        DatabaseController.getInstance().updateCollection(collection);

        mAdapter.notifyDataSetChanged();

    }

    private void clearSelection() {
        mMultiSelector.clearSelections();
        mSelectedItems.clear();
        mMultiSelector.setSelectable(false);
    }

}
