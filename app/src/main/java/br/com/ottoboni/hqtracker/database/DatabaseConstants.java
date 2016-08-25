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

import android.provider.BaseColumns;

/**
 * The type Database constants.
 */
public class DatabaseConstants {

    /**
     * The constant DEFAULT_DB_INT.
     */
    public static final long DEFAULT_DB_INT = -1;
    /**
     * The constant INVALID_INSERT.
     */
    public static final long INVALID_INSERT = -1;
    /**
     * The constant EMPTY_QUERY.
     */
    public static final int EMPTY_QUERY = 0;
    /**
     * The constant EMPTY_VALUES.
     */
    public static final int EMPTY_VALUES = 0;
    /**
     * The constant DEFAULT_VALUE.
     */
    public static final int DEFAULT_VALUE = 0;

    public static final String EQUAL_CLAUSE = " = ? ";

    public static final String AND_CLAUSE = " AND ";

    /**
     * The type Comic book table.
     */
    public static class ComicBookTable implements BaseColumns {

        /**
         * The constant TABLE_NAME.
         */
        public static final String TABLE_NAME = "tb_comic_book";

        /**
         * The constant VOLUME.
         */
        public static final String VOLUME = "volume";
        /**
         * The constant TITLE.
         */
        public static final String TITLE = "title";
        /**
         * The constant BOOK_SPINE.
         */
        public static final String BOOK_SPINE = "book_spine";
        /**
         * The constant URL.
         */
        public static final String URL = "url";
        /**
         * The constant READ_ORDER.
         */
        public static final String READ_ORDER = "read_order";
        /**
         * The constant COLLECTION_NAME.
         */
        public static final String COLLECTION_NAME = "collection_name";
        /**
         * The constant COLLECTION_ID.
         */
        public static final String COLLECTION_ID = "collection_id";
        /**
         * The constant IMG_LARGE.
         */
        public static final String IMG_LARGE = "img_large";
        /**
         * The constant IMG_SMALL.
         */
        public static final String IMG_SMALL = "img_small";
        /**
         * The constant STATUS.
         */
        public static final String STATUS = "status";

    }

    /**
     * The type Collection table.
     */
    public static class CollectionTable implements BaseColumns {

        /**
         * The constant TABLE_NAME.
         */
        public static final String TABLE_NAME = "tb_collection";

        /**
         * The constant COLLECTION_ID.
         */
        public static final String COLLECTION_ID = "collection_id";
        /**
         * The constant COLLECTION_NAME.
         */
        public static final String COLLECTION_NAME = "collection_name";
        /**
         * The constant STATUS.
         */
        public static final String STATUS = "status";
        /**
         * The constant PUBLISHING.
         */
        public static final String PUBLISHING = "publishing";
    }
}
