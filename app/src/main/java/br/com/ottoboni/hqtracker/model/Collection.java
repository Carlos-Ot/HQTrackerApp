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

package br.com.ottoboni.hqtracker.model;

import br.com.ottoboni.hqtracker.model.enums.CollectionStatus;

/**
 * The type Collection.
 */
public class Collection {

    private long id;
    private String collectionId;
    private String collectionName;
    private CollectionStatus status;
    private int publishing;

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets collection id.
     *
     * @return the collection id
     */
    public String getCollectionId() {
        return collectionId;
    }

    /**
     * Sets collection id.
     *
     * @param collectionId the collection id
     */
    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    /**
     * Gets collection name.
     *
     * @return the collection name
     */
    public String getCollectionName() {
        return collectionName;
    }

    /**
     * Sets collection name.
     *
     * @param collectionName the collection name
     */
    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public CollectionStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(CollectionStatus status) {
        this.status = status;
    }

    /**
     * Gets publishing.
     *
     * @return the publishing
     */
    public int getPublishing() {
        return publishing;
    }

    /**
     * Sets publishing.
     *
     * @param publishing the publishing
     */
    public void setPublishing(int publishing) {
        this.publishing = publishing;
    }
}
