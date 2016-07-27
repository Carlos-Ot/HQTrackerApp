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

import br.com.ottoboni.hqtracker.model.enums.ComicBookStatus;

/**
 * The type Comic book.
 */
public class ComicBook {

    private long id;
    private int volume;
    private String title;
    private String bookSpine;
    private String url;
    private int readOrder;
    private String collectionName;
    private String collectionId;
    private String imgLarge;
    private String imgSmall;
    private ComicBookStatus status;

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
     * Gets volume.
     *
     * @return the volume
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Sets volume.
     *
     * @param volume the volume
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets book spine.
     *
     * @return the book spine
     */
    public String getBookSpine() {
        return bookSpine;
    }

    /**
     * Sets book spine.
     *
     * @param bookSpine the book spine
     */
    public void setBookSpine(String bookSpine) {
        this.bookSpine = bookSpine;
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url.
     *
     * @param url the url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets read order.
     *
     * @return the read order
     */
    public int getReadOrder() {
        return readOrder;
    }

    /**
     * Sets read order.
     *
     * @param readOrder the read order
     */
    public void setReadOrder(int readOrder) {
        this.readOrder = readOrder;
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
     * Gets img large.
     *
     * @return the img large
     */
    public String getImgLarge() {
        return imgLarge;
    }

    /**
     * Sets img large.
     *
     * @param imgLarge the img large
     */
    public void setImgLarge(String imgLarge) {
        this.imgLarge = imgLarge;
    }

    /**
     * Gets img small.
     *
     * @return the img small
     */
    public String getImgSmall() {
        return imgSmall;
    }

    /**
     * Sets img small.
     *
     * @param imgSmall the img small
     */
    public void setImgSmall(String imgSmall) {
        this.imgSmall = imgSmall;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public ComicBookStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(ComicBookStatus status) {
        this.status = status;
    }
}
