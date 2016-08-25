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

package br.com.ottoboni.hqtracker.controllers;

import java.util.ArrayList;
import java.util.List;

import br.com.ottoboni.hqtracker.database.CollectionDAO;
import br.com.ottoboni.hqtracker.database.ComicBookDAO;
import br.com.ottoboni.hqtracker.model.Collection;
import br.com.ottoboni.hqtracker.model.ComicBook;
import br.com.ottoboni.hqtracker.model.enums.CollectionStatus;
import br.com.ottoboni.hqtracker.model.enums.ComicBookStatus;

/**
 * The type Database controller.
 */
public class DatabaseController {

    private static DatabaseController mInstance;

    private ComicBookDAO mComicBookDAO;
    private CollectionDAO mCollectionDAO;

    private DatabaseController() {
        mComicBookDAO = new ComicBookDAO();
        mCollectionDAO = new CollectionDAO();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static DatabaseController getInstance() {
        if (mInstance == null) {
            mInstance = new DatabaseController();
        }

        return mInstance;
    }
    /********************************************/
    /*************** ComicBookDAO ***************/
    /**
     * Insert comic book long.
     *
     * @param comicBook the comic book
     *
     * @return the long
     */

    public long insertComicBook(ComicBook comicBook) {
        return mComicBookDAO.insertComicBook(comicBook);
    }

    /**
     * Insert comic book list.
     *
     * @param comicBookList the comic book array list
     *
     * @return the boolean
     */
    public boolean insertComicBookList(List<ComicBook> comicBookList) {
        return mComicBookDAO.insertComicBookList(comicBookList);
    }

    /**
     * Update comic book boolean.
     *
     * @param comicBook the comic book
     *
     * @return the boolean
     */
    public boolean updateComicBook(ComicBook comicBook) {
        return mComicBookDAO.updateComicBook(comicBook);
    }

    /**
     * Delete comic book.
     *
     * @param comicBookId the comic book id
     */
    public void deleteComicBook(long comicBookId) {
        mComicBookDAO.deleteComicBook(comicBookId);
    }

    /**
     * Gets comic book by id.
     *
     * @param comicBookId the comic book id
     *
     * @return the comic book by id
     */
    public ComicBook getComicBookById(long comicBookId) {
        return mComicBookDAO.getComicBookById(comicBookId);
    }

    /**
     * Gets acquired comic books.
     *
     * @param collectionId the collection id
     *
     * @return the acquired comic books
     */
    public List<ComicBook> getAcquiredComicBooks(String collectionId) {
        return mComicBookDAO.getComicBooksByStatus(collectionId, ComicBookStatus.acquired);
    }

    /**
     * Gets pending comic books.
     *
     * @param collectionId the collection id
     *
     * @return the pending comic books
     */
    public List<ComicBook> getPendingComicBooks(String collectionId) {
        return mComicBookDAO.getComicBooksByStatus(collectionId, ComicBookStatus.pending);
    }

    /**
     * Gets unavailable comic books.
     *
     * @param collectionId the collection id
     *
     * @return the unavailable comic books
     */
    public List<ComicBook> getUnavailableComicBooks(String collectionId) {
        return mComicBookDAO.getComicBooksByStatus(collectionId, ComicBookStatus.unavailable);
    }

    /**
     * Gets missing comic books.
     *
     * @param collectionId the collection id
     *
     * @return the missing comic books
     */
    public List<ComicBook> getMissingComicBooks(String collectionId) {
        return mComicBookDAO.getComicBooksByStatus(collectionId, ComicBookStatus.missing);
    }

    /**
     * Count acquired comic books long.
     *
     * @param collectionId the collection id
     *
     * @return the long
     */
    public long countAcquiredComicBooks(String collectionId) {
        return mComicBookDAO.countComicBooksByStatus(collectionId, ComicBookStatus.acquired);
    }

    /**
     * Count comic books long.
     *
     * @param collectionId the collection id
     *
     * @return the long
     */
    public long countComicBooks(String collectionId) {
        return mComicBookDAO.countComicBooksForCollection(collectionId);
    }

    /********************************************/
    /***************
     * Insert collection long.
     *
     * @param collection the collection
     *
     * @return the long CollectionDAO
     **************/

    /***************
     * CollectionDAO  @param collection the collection
     *
     * @return the long
     */
    public long insertCollection(Collection collection) {
        return mCollectionDAO.insertCollection(collection);
    }

    /**
     * Insert collection list.
     *
     * @param collectionArrayList the collection array list
     *
     * @return the boolean
     */
    public boolean insertCollectionList(List<Collection> collectionArrayList) {
       return mCollectionDAO.insertCollectionList(collectionArrayList);
    }

    /**
     * Update collection boolean.
     *
     * @param collection the collection
     *
     * @return the boolean
     */
    public boolean updateCollection(Collection collection) {
        return mCollectionDAO.updateCollection(collection);
    }

    /**
     * Delete collection.
     *
     * @param collectionId the collection id
     */
    public void deleteCollection(long collectionId) {
        mCollectionDAO.deleteCollection(collectionId);
    }

    /**
     * Gets collection.
     *
     * @param collectionId the collection id
     *
     * @return the collection
     */
    public Collection getCollection(long collectionId) {
        return mCollectionDAO.getCollectionById(collectionId);
    }

    /**
     * Gets collections.
     *
     * @return the collections
     */
    public List<Collection> getCollections() {
        return mCollectionDAO.getCollections();
    }

    /**
     * Gets tracking collections.
     *
     * @return the tracking collections
     */
    public List<Collection> getTrackingCollections() {
        return mCollectionDAO.getCollectionsByStatus(CollectionStatus.tracking);
    }

    /**
     * Gets paused collections.
     *
     * @return the paused collections
     */
    public List<Collection> getPausedCollections() {
        return mCollectionDAO.getCollectionsByStatus(CollectionStatus.paused);
    }

    /**
     * Gets finished collections.
     *
     * @return the finished collections
     */
    public List<Collection> getFinishedCollections() {
        return mCollectionDAO.getCollectionsByStatus(CollectionStatus.finished);
    }

    /**
     * Gets untracked collections.
     *
     * @return the untracked collections
     */
    public List<Collection> getUntrackedCollections() {
        return mCollectionDAO.getCollectionsByStatus(CollectionStatus.untracked);
    }
}
