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

package br.com.ottoboni.hqtracker.model.enums;

import android.support.annotation.IntRange;

import java.util.HashMap;
import java.util.Map;

public enum HttpStatus {

    SUCCESS(200),
    UNKNOWN_ERROR(666),
    TIMEOUT(408),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    FORBIDDEN(403),
    INTERNAL_SERVER_ERROR(500),
    METHOD_NOT_ALLOWED(405),
    NO_CONNECTION_AVAILABLE(998),
    JSON_SYNTAX_RESULT(1001),
    GONE(410);

    private static Map<Integer, HttpStatus> mHttpStatusCodes = null;
    private int mHttpStatusCode;

    HttpStatus(int statusCode) { mHttpStatusCode = statusCode;}

    private static void buildHttpStatusMap() {
        if (mHttpStatusCodes == null) {
            mHttpStatusCodes = new HashMap<>();
            HttpStatus[] values = values();

            for (HttpStatus code : values) {
                mHttpStatusCodes.put(code.getCode(), code);
            }
        }
    }

    public static HttpStatus valueOf(int value) {
        HttpStatus status = mHttpStatusCodes.get(value);
        if (status == null) {
            status = UNKNOWN_ERROR;
        }

        return status;
    }

    public int getCode() { return mHttpStatusCode; }
}
