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
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.ottoboni.hqtracker.model.ComicBook;
import br.com.ottoboni.hqtracker.model.enums.ComicBookStatus;

/**
 * The type Comic book dao.
 */
public class ComicBookDAO {

    private SQLiteDatabase mDatabase;

    /**
     * Instantiates a new Comic book dao.
     */
    public ComicBookDAO() {
        DatabaseHelper helper = new DatabaseHelper();
        mDatabase = helper.getWritableDatabase();
    }

    /**
     * Insert comic book long.
     *
     * @param comicBook the comic book
     *
     * @return the long
     */
    public long insertComicBook(ComicBook comicBook) {
        long insertedId = DatabaseConstants.DEFAULT_DB_INT;

        if (comicBook != null) {
            ContentValues values = getContentValues(comicBook);

            if (values.size() > DatabaseConstants.EMPTY_VALUES) {
                insertedId = mDatabase.insert(DatabaseConstants.ComicBookTable.TABLE_NAME, null, values);
            }
        }
        return insertedId;
    }

    /**
     * Insert comic book list.
     *
     * @param comicBookList the comic book list
     */
    public void insertComicBookList(ArrayList<ComicBook> comicBookList) {
        int insertedRows = DatabaseConstants.DEFAULT_VALUE;

        if (comicBookList != null && comicBookList.size() > DatabaseConstants.EMPTY_VALUES) {
            for (ComicBook comicBook : comicBookList) {
                long insertedId = insertComicBook(comicBook);

                if (insertedId != DatabaseConstants.DEFAULT_DB_INT) {
                    insertedRows++;
                }
            }

            if (insertedRows != comicBookList.size()) {
                //TODO: Replace with log class
                Log.i("comicBookDAO", "Some items were not inserted");
            } else {
                Log.i("comicBookDAO", "All items were inserted!");
            }
        }
    }

    /**
     * Update comic book boolean.
     *
     * @param comicBook the comic book
     *
     * @return the boolean
     */
    public boolean updateComicBook(@NonNull ComicBook comicBook) {
        boolean result = false;
        long updatedRows;
        ContentValues values = getContentValues(comicBook);

        if (values.size() != DatabaseConstants.EMPTY_VALUES) {

            String whereClause = DatabaseConstants.ComicBookTable._ID + DatabaseConstants.EQUAL_CLAUSE;
            String[] whereArgs = {String.valueOf(comicBook.getId())};

            updatedRows = mDatabase.update(DatabaseConstants.ComicBookTable.TABLE_NAME, values,
                whereClause, whereArgs);

            result = updatedRows > DatabaseConstants.EMPTY_QUERY;
        }

        return result;
    }

    /**
     * Delete comic book.
     *
     * @param comicBookId the comic book id
     */
    public void deleteComicBook(long comicBookId) {
        String whereClause = DatabaseConstants.ComicBookTable._ID + DatabaseConstants.EQUAL_CLAUSE;
        String[] whereArgs = {String.valueOf(comicBookId)};

        mDatabase.delete(DatabaseConstants.ComicBookTable.TABLE_NAME, whereClause, whereArgs);
    }

    /**
     * Gets comic book by id.
     *
     * @param comicBookId the comic book id
     *
     * @return the comic book by id
     */
    public ComicBook getComicBookById(long comicBookId) {
        ComicBook comicBook = new ComicBook();

        String query = DatabaseConstants.ComicBookTable._ID + DatabaseConstants.EQUAL_CLAUSE;
        String[] whereArgs = {String.valueOf(comicBookId)};

        Cursor cursor = mDatabase.query(DatabaseConstants.ComicBookTable.TABLE_NAME, null, query,
            whereArgs, null, null, null);

        if (cursor.moveToFirst()) {
            comicBook = buildComicBookInfo(cursor);
        }

        if (!cursor.isClosed()) {
            cursor.close();
        }

        return comicBook;
    }

    /**
     * Gets comic books by collection.
     *
     * @param collectionId the collection id
     *
     * @return the comic books by collection
     */
    public List<ComicBook> getComicBooksByCollection(String collectionId) {
        String whereClause = DatabaseConstants.ComicBookTable.COLLECTION_ID + DatabaseConstants.EQUAL_CLAUSE;
        String[] whereArgs = {collectionId};

        return getComicBookList(whereClause, whereArgs);
    }

    /**
     * Gets comic books by status.
     *
     * @param status the status
     *
     * @return the comic books by status
     */
    public List<ComicBook> getComicBooksByStatus(ComicBookStatus status) {
        String whereClause = DatabaseConstants.ComicBookTable.STATUS + DatabaseConstants.EQUAL_CLAUSE;
        String[] whereArgs = {String.valueOf(status.ordinal())};

        return getComicBookList(whereClause, whereArgs);
    }

    private List<ComicBook> getComicBookList(String whereClause, String[] whereArgs) {
        List<ComicBook> comicBookList = new ArrayList<>();
        ComicBook comicBook;

        Cursor cursor = mDatabase.query(DatabaseConstants.ComicBookTable.TABLE_NAME,
            null,
            whereClause,
            whereArgs,
            null, null, null);

        if (cursor.moveToFirst()) {
            do {
                comicBook = buildComicBookInfo(cursor);
                comicBookList.add(comicBook);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        if (!cursor.isClosed()) {
            cursor.close();
        }

        return comicBookList;
    }

    /**
     * Remove all data.
     */
    public void removeAllData() {
        mDatabase.delete(DatabaseConstants.ComicBookTable.TABLE_NAME, null, null);
    }

    private ContentValues getContentValues(ComicBook comicBook) {
        ContentValues values = new ContentValues();

        if (comicBook != null) {
            values.put(DatabaseConstants.ComicBookTable.VOLUME, comicBook.getVolume());
            values.put(DatabaseConstants.ComicBookTable.TITLE, comicBook.getTitle());
            values.put(DatabaseConstants.ComicBookTable.BOOK_SPINE, comicBook.getBookSpine());
            values.put(DatabaseConstants.ComicBookTable.URL, comicBook.getUrl());
            values.put(DatabaseConstants.ComicBookTable.READ_ORDER, comicBook.getReadOrder());
            values.put(DatabaseConstants.ComicBookTable.COLLECTION_NAME, comicBook.getCollectionName());
            values.put(DatabaseConstants.ComicBookTable.COLLECTION_ID, comicBook.getCollectionId());
            values.put(DatabaseConstants.ComicBookTable.IMG_LARGE, comicBook.getImgLarge());
            values.put(DatabaseConstants.ComicBookTable.IMG_SMALL, comicBook.getImgSmall());
            values.put(DatabaseConstants.ComicBookTable.STATUS, comicBook.getStatus().ordinal());
        }

        return values;
    }
    private ComicBook buildComicBookInfo(Cursor cursor) {
        ComicBook comicBook = new ComicBook();

        int indexId = cursor.getColumnIndex(DatabaseConstants.ComicBookTable._ID);
        int indexVolume = cursor.getColumnIndex(DatabaseConstants.ComicBookTable.VOLUME);
        int indexTitle = cursor.getColumnIndex(DatabaseConstants.ComicBookTable.TITLE);
        int indexBookSpine = cursor.getColumnIndex(DatabaseConstants.ComicBookTable.BOOK_SPINE);
        int indexUrl = cursor.getColumnIndex(DatabaseConstants.ComicBookTable.URL);
        int indexReadOrder = cursor.getColumnIndex(DatabaseConstants.ComicBookTable.READ_ORDER);
        int indexCollectionName = cursor.getColumnIndex(DatabaseConstants.ComicBookTable.COLLECTION_NAME);
        int indexCollectionId = cursor.getColumnIndex(DatabaseConstants.ComicBookTable.COLLECTION_ID);
        int indexImgLarge = cursor.getColumnIndex(DatabaseConstants.ComicBookTable.IMG_LARGE);
        int indexImgSmall = cursor.getColumnIndex(DatabaseConstants.ComicBookTable.IMG_SMALL);
        int indexComicBookStatus = cursor.getColumnIndex(DatabaseConstants.ComicBookTable.STATUS);

        final long id = cursor.getLong(indexId);
        int volume = cursor.getInt(indexVolume);
        String title = cursor.getString(indexTitle);
        String bookSpine = cursor.getString(indexBookSpine);
        String url = cursor.getString(indexUrl);
        int readOrder = cursor.getInt(indexReadOrder);
        String collectionName = cursor.getString(indexCollectionName);
        String collectionId = cursor.getString(indexCollectionId);
        String imgLarge = cursor.getString(indexImgLarge);
        String imgSmall = cursor.getString(indexImgSmall);

        int statusInt = cursor.getInt(indexComicBookStatus);
        ComicBookStatus comicBookStatus = ComicBookStatus.valueOf(statusInt);

        comicBook.setId(id);
        comicBook.setVolume(volume);
        comicBook.setTitle(title);
        comicBook.setBookSpine(bookSpine);
        comicBook.setUrl(url);
        comicBook.setReadOrder(readOrder);
        comicBook.setCollectionName(collectionName);
        comicBook.setCollectionId(collectionId);
        comicBook.setImgLarge(imgLarge);
        comicBook.setImgSmall(imgSmall);
        comicBook.setStatus(comicBookStatus);

        return comicBook;
    }
}
