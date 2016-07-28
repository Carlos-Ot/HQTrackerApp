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

package br.com.ottoboni.hqtracker.communication;

import java.util.List;

import br.com.ottoboni.hqtracker.communication.model.CollectionResponse;
import br.com.ottoboni.hqtracker.communication.model.ComicBookResponse;
import retrofit.Call;
import retrofit.http.GET;

public interface ApiRequest {

    @GET("/comic_books")
    Call<List<ComicBookResponse>> getComicBooks();

    @GET("/collections")
    Call<List<CollectionResponse>> getCollections();
}
