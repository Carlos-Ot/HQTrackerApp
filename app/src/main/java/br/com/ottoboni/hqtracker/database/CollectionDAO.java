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

package br.com.ottoboni.hqtracker.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.ottoboni.hqtracker.model.Collection;
import br.com.ottoboni.hqtracker.model.enums.CollectionStatus;

/**
 * The type Collection dao.
 */
public class CollectionDAO {

    private SQLiteDatabase mDatabase;

    /**
     * Instantiates a new Collection dao.
     */
    public CollectionDAO() {
        DatabaseHelper helper = new DatabaseHelper();
        helper.getWritableDatabase();
    }

    /**
     * Insert collection long.
     *
     * @param collection the collection
     *
     * @return the long
     */
    public long insertCollection(Collection collection) {
        long insertedId = DatabaseConstants.DEFAULT_DB_INT;

        if (collection != null) {
            ContentValues values = getContentValues(collection);

            if (values.size() > DatabaseConstants.EMPTY_VALUES) {
                insertedId = mDatabase.insert(DatabaseConstants.CollectionTable.TABLE_NAME, null, values);
            }
        }
        return insertedId;
    }

    /**
     * Insert collection list.
     *
     * @param collectionList the collection list
     */
    public void insertCollectionList(List<Collection> collectionList) {
        int insertedRows = DatabaseConstants.DEFAULT_VALUE;

        if (collectionList != null && collectionList.size() > DatabaseConstants.EMPTY_VALUES) {
            for (Collection collection : collectionList) {
                long insertedId = insertCollection(collection);

                if (insertedId != DatabaseConstants.DEFAULT_DB_INT) {
                    insertedRows++;
                }
            }

            if (insertedRows != collectionList.size()) {
                //TODO: Replace with log class
                Log.i("CollectionDAO", "Some items were not inserted");
            } else {
                Log.i("CollectionDAO", "All items were inserted!");
            }
        }
    }

    /**
     * Update collection boolean.
     *
     * @param collection the collection
     *
     * @return the boolean
     */
    public boolean updateCollection(@NonNull Collection collection) {
        boolean result = false;
        long updatedRows;
        ContentValues values = getContentValues(collection);

        if (values.size() != DatabaseConstants.EMPTY_VALUES) {

            String whereClause = DatabaseConstants.CollectionTable._ID + DatabaseConstants.EQUAL_CLAUSE;
            String[] parameters = {String.valueOf(collection.getId())};

            updatedRows = mDatabase.update(DatabaseConstants.CollectionTable.TABLE_NAME, values,
                whereClause, parameters);

            result = updatedRows > DatabaseConstants.EMPTY_QUERY;
        }

        return result;
    }

    /**
     * Delete collection.
     *
     * @param collection the collection
     */
    public void deleteCollection(long collection) {
        String whereClause = DatabaseConstants.CollectionTable._ID + DatabaseConstants.EQUAL_CLAUSE;
        String[] parameters = {String.valueOf(collection)};

        mDatabase.delete(DatabaseConstants.CollectionTable.TABLE_NAME, whereClause, parameters);
    }

    /**
     * Gets collection by id.
     *
     * @param collectionId the collection id
     *
     * @return the collection by id
     */
    public Collection getCollectionById(long collectionId) {
        Collection collection = new Collection();

        String query = DatabaseConstants.CollectionTable._ID + DatabaseConstants.EQUAL_CLAUSE;
        String[] parameters = {String.valueOf(collectionId)};

        Cursor cursor = mDatabase.query(DatabaseConstants.CollectionTable.TABLE_NAME, null, query,
            parameters, null, null, null);

        if (cursor.moveToFirst()) {
            collection = buildCollectionInfo(cursor);
        }

        if (!cursor.isClosed()) {
            cursor.close();
        }

        return collection;
    }

    /**
     * Gets collections.
     *
     * @return the collections
     */
    public List<Collection> getCollections() {
        return getCollectionList(null, null);
    }

    /**
     * Gets collections by status.
     *
     * @param status the status
     *
     * @return the collections by status
     */
    public List<Collection> getCollectionsByStatus(CollectionStatus status) {
        String whereClause = DatabaseConstants.CollectionTable.STATUS + DatabaseConstants.EQUAL_CLAUSE;
        String[] whereArgs = {String.valueOf(status.ordinal())};

        return getCollectionList(whereClause, whereArgs);
    }

    private List<Collection> getCollectionList(@Nullable String whereClause, @Nullable String[] whereArgs) {
        List<Collection> collectionList = new ArrayList<>();
        Collection collection;

        Cursor cursor = mDatabase.query(DatabaseConstants.CollectionTable.TABLE_NAME,
            null,
            whereClause,
            whereArgs,
            null, null, null);

        if (cursor.moveToFirst()) {
            do {
                collection = buildCollectionInfo(cursor);
                collectionList.add(collection);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        if (!cursor.isClosed()) {
            cursor.close();
        }

        return collectionList;
    }

    private ContentValues getContentValues(Collection collection) {
        ContentValues values = new ContentValues();

        if (collection != null) {
            values.put(DatabaseConstants.CollectionTable.COLLECTION_ID, collection.getCollectionId());
            values.put(DatabaseConstants.CollectionTable.COLLECTION_NAME, collection.getCollectionName());
            values.put(DatabaseConstants.CollectionTable.PUBLISHING, collection.getPublishing());
            values.put(DatabaseConstants.CollectionTable.STATUS, collection.getStatus().ordinal());
        }

        return values;
    }

    private Collection buildCollectionInfo(Cursor cursor) {
        Collection collection = new Collection();

        int indexId = cursor.getColumnIndex(DatabaseConstants.CollectionTable._ID);
        int indexCollectionId = cursor.getColumnIndex(DatabaseConstants.CollectionTable.COLLECTION_ID);
        int indexCollectionName = cursor.getColumnIndex(DatabaseConstants.CollectionTable.COLLECTION_NAME);
        int indexPublishing = cursor.getColumnIndex(DatabaseConstants.CollectionTable.PUBLISHING);
        int indexStatus = cursor.getColumnIndex(DatabaseConstants.CollectionTable.STATUS);

        long id = cursor.getLong(indexId);
        String collectionId = cursor.getString(indexCollectionId);
        String collectionName = cursor.getString(indexCollectionName);
        int publishing = cursor.getInt(indexPublishing);
        int statusInt = cursor.getInt(indexStatus);

        CollectionStatus status = CollectionStatus.valueOf(statusInt);

        collection.setId(id);
        collection.setCollectionId(collectionId);
        collection.setCollectionName(collectionName);
        collection.setPublishing(publishing);
        collection.setStatus(status);

        return collection;
    }
}
