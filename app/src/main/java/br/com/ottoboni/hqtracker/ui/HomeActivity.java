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
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.ottoboni.hqtracker.R;
import br.com.ottoboni.hqtracker.app.App;
import br.com.ottoboni.hqtracker.controllers.DatabaseController;
import br.com.ottoboni.hqtracker.model.Collection;
import br.com.ottoboni.hqtracker.ui.adapter.CollectionAdapter;
import br.com.ottoboni.hqtracker.ui.view.ItemDivider;
import br.com.ottoboni.hqtracker.util.FontUtil;

public class HomeActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener, CollectionAdapter.ViewHolder
    .CollectionItemClickListener {

    private TextView mEmptyView;

    private CollectionAdapter mAdapter;
    private List<Collection> mItems;
    private FloatingActionButton fab;

    private RecyclerView collectionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();
        loadListItems();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, CollectionActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initView() {

        collectionList = (RecyclerView) findViewById(R.id.collection_list);
        mEmptyView = (TextView) findViewById(R.id.empty_list);

        mEmptyView.setTypeface(FontUtil.ROBOTO_REGULAR);
    }

    private void loadListItems() {

        mItems = DatabaseController.getInstance().getTrackingCollections();

        if (!mItems.isEmpty()) {

            mEmptyView.setVisibility(View.GONE);
            collectionList.setVisibility(View.VISIBLE);

            mAdapter = new CollectionAdapter(mItems, this);

            collectionList.setHasFixedSize(true);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);

            collectionList.setLayoutManager(mLayoutManager);
            collectionList.setAdapter(mAdapter);
            collectionList.addItemDecoration(new ItemDivider(this));

            collectionList.addOnScrollListener(mScrollListener);
        } else {
            mEmptyView.setVisibility(View.VISIBLE);
            collectionList.setVisibility(View.GONE);
        }
    }

    private RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (dy > 0) {
                fab.hide();
            } else if (dy < 0) {
                fab.show();
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();

        if (mAdapter != null) {
            mItems = DatabaseController.getInstance().getTrackingCollections();
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_collections) {
            // Handle the camera action
        } else if (id == R.id.nav_publishers) {

        } else if (id == R.id.nav_about) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(View view) {
        int position = collectionList.getChildAdapterPosition(view);
        String collectionId = mItems.get(position).getCollectionId();

        Intent intent = new Intent(HomeActivity.this, ComicBookActivity.class);
        intent.putExtra(ComicBookActivity.KEY_COLLECTION_ID, collectionId);
        startActivity(intent);
    }
}
