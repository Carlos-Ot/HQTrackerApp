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

package br.com.ottoboni.hqtracker.communication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CollectionResponse {

    @SerializedName("collection_id")
    @Expose
    private String collectionId;
    @SerializedName("collection_name")
    @Expose
    private String collectionName;
    @SerializedName("collection_url")
    @Expose
    private String collectionUrl;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("publishing")
    @Expose
    private int publishing;
    @SerializedName("last_modified")
    @Expose
    private String lastModified;

    /**
     *
     * @return
     * The collectionId
     */
    public String getCollectionId() {
        return collectionId;
    }

    /**
     *
     * @param collectionId
     * The collection_id
     */
    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    /**
     *
     * @return
     * The collectionName
     */
    public String getCollectionName() {
        return collectionName;
    }

    /**
     *
     * @param collectionName
     * The collection_name
     */
    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    /**
     *
     * @return
     * The collectionUrl
     */
    public String getCollectionUrl() {
        return collectionUrl;
    }

    /**
     *
     * @param collectionUrl
     * The collection_url
     */
    public void setCollectionUrl(String collectionUrl) {
        this.collectionUrl = collectionUrl;
    }

    /**
     *
     * @return
     * The status
     */
    public int getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The publishing
     */
    public int getPublishing() {
        return publishing;
    }

    /**
     *
     * @param publishing
     * The publishing
     */
    public void setPublishing(int publishing) {
        this.publishing = publishing;
    }

    /**
     *
     * @return
     * The lastModified
     */
    public String getLastModified() {
        return lastModified;
    }

    /**
     *
     * @param lastModified
     * The last_modified
     */
    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

}
