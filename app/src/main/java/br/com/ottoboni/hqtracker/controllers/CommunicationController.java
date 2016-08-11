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

import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.List;

import br.com.ottoboni.hqtracker.communication.ApiClient;
import br.com.ottoboni.hqtracker.communication.ApiRequest;
import br.com.ottoboni.hqtracker.communication.model.CollectionResponse;
import br.com.ottoboni.hqtracker.communication.model.ComicBookResponse;
import br.com.ottoboni.hqtracker.model.Collection;
import br.com.ottoboni.hqtracker.model.ComicBook;
import br.com.ottoboni.hqtracker.model.enums.HttpStatus;
import br.com.ottoboni.hqtracker.parses.ResponseParser;
import retrofit.Response;

public class CommunicationController {

    private static final String TAG = "CommunicationController";
    private static CommunicationController mInstance;

    private CommunicationController() {}

    public static CommunicationController getInstance() {
        if (mInstance == null) {
            mInstance = new CommunicationController();
        }
        return mInstance;
    }

    public HttpStatus requestComicBookList() {
        List<ComicBookResponse> comicBooks = null;
        ApiRequest apiRequest = ApiClient.createRequests();
        boolean result = false;
        HttpStatus status = HttpStatus.UNKNOWN_ERROR;

        try {

            Response<List<ComicBookResponse>> response = apiRequest.getComicBooks().execute();

            if (response != null && response.body() != null) {
                comicBooks = response.body();

                List<ComicBook> comicBookList = ResponseParser.parseComicBookList(comicBooks);

                result = DatabaseController.getInstance().insertComicBookList(comicBookList);
            }

        } catch (ConnectException e) {
            status = HttpStatus.NO_CONNECTION_AVAILABLE;
        } catch (SocketTimeoutException e) {
            status = HttpStatus.TIMEOUT;
        } catch (IOException e) {
            status = HttpStatus.UNKNOWN_ERROR;
        } catch (JsonSyntaxException e) {
            status = HttpStatus.JSON_SYNTAX_RESULT;
        }

        if (result) {
            status = HttpStatus.SUCCESS;
        }

        return status;
    }

    public HttpStatus requestCollections() {
        List<CollectionResponse> collections;
        ApiRequest apiRequest = ApiClient.createRequests();
        boolean result = false;
        HttpStatus status = HttpStatus.UNKNOWN_ERROR;

        try {

            Response<List<CollectionResponse>> response = apiRequest.getCollections().execute();

            if (response != null && response.body() != null) {
                collections = response.body();

                List<Collection> collectionList = ResponseParser.parseCollectionList(collections);

                result = DatabaseController.getInstance().insertCollectionList(collectionList);
            }

        } catch (ConnectException e) {
            status = HttpStatus.NO_CONNECTION_AVAILABLE;
        } catch (SocketTimeoutException e) {
            status = HttpStatus.TIMEOUT;
        } catch (IOException e) {
            status = HttpStatus.UNKNOWN_ERROR;
        } catch (JsonSyntaxException e) {
            status = HttpStatus.JSON_SYNTAX_RESULT;
        }

        if (result) {
            status = HttpStatus.SUCCESS;
        }

        return status;
    }
}
