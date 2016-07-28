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

import android.support.annotation.VisibleForTesting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Comic book.
 */
public class ComicBookResponse {

    @SerializedName("comic_book_id")
    @Expose
    private int comicBookId;
    @SerializedName("volume")
    @Expose
    private int volume;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("comic_book_spine")
    @Expose
    private String comicBookSpine;
    @SerializedName("comic_book_url")
    @Expose
    private String comicBookUrl;
    @SerializedName("read_order")
    @Expose
    private int readOrder;
    @SerializedName("collection")
    @Expose
    private String collection;
    @SerializedName("img_large")
    @Expose
    private String imgLarge;
    @SerializedName("img_small")
    @Expose
    private String imgSmall;

    public int getComicBookId() {
        return comicBookId;
    }

    public void setComicBookId(int comicBookId) {
        this.comicBookId = comicBookId;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComicBookSpine() {
        return comicBookSpine;
    }

    public void setComicBookSpine(String comicBookSpine) {
        this.comicBookSpine = comicBookSpine;
    }

    public String getComicBookUrl() {
        return comicBookUrl;
    }

    public void setComicBookUrl(String comicBookUrl) {
        this.comicBookUrl = comicBookUrl;
    }

    public int getReadOrder() {
        return readOrder;
    }

    public void setReadOrder(int readOrder) {
        this.readOrder = readOrder;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getImgLarge() {
        return imgLarge;
    }

    public void setImgLarge(String imgLarge) {
        this.imgLarge = imgLarge;
    }

    public String getImgSmall() {
        return imgSmall;
    }

    public void setImgSmall(String imgSmall) {
        this.imgSmall = imgSmall;
    }
}
