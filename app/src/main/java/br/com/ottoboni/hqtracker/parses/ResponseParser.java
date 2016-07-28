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

package br.com.ottoboni.hqtracker.parses;

import br.com.ottoboni.hqtracker.communication.model.CollectionResponse;
import br.com.ottoboni.hqtracker.communication.model.ComicBookResponse;
import br.com.ottoboni.hqtracker.model.Collection;
import br.com.ottoboni.hqtracker.model.ComicBook;
import br.com.ottoboni.hqtracker.model.enums.CollectionStatus;
import br.com.ottoboni.hqtracker.model.enums.ComicBookStatus;

public class ResponseParser {

    public static ComicBook parseComicBook(ComicBookResponse comicBookResponse) {
        ComicBook comicBook = new ComicBook();

        if (comicBookResponse != null) {
            comicBook.setVolume(comicBookResponse.getVolume());
            comicBook.setTitle(comicBookResponse.getTitle());
            comicBook.setBookSpine(comicBookResponse.getComicBookSpine());
            comicBook.setUrl(comicBookResponse.getComicBookUrl());
            comicBook.setReadOrder(comicBookResponse.getReadOrder());
            comicBook.setCollectionId(comicBookResponse.getCollection());
            comicBook.setImgLarge(comicBookResponse.getImgLarge());
            comicBook.setImgSmall(comicBookResponse.getImgSmall());
        }
        return comicBook;
    }

    public static Collection parseCollection(CollectionResponse collectionResponse) {
        Collection collection = new Collection();

        if (collectionResponse != null) {
            collection.setCollectionId(collectionResponse.getCollectionId());
            collection.setCollectionName(collectionResponse.getCollectionName());
            collection.setPublishing(collectionResponse.getPublishing());
            collection.setStatus(CollectionStatus.valueOf(collectionResponse.getStatus()));
        }

        return collection;
    }

}
