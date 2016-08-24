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

import android.graphics.Color;
import android.support.annotation.IntRange;

import br.com.ottoboni.hqtracker.R;
import br.com.ottoboni.hqtracker.app.App;

/**
 * The enum User status.
 */
public enum ComicBookStatus {

    /**
     * Acquired user status.
     */
    acquired,

    /**
     * Pending user status.
     */
    pending,

    /**
     * Unavailable user status.
     */
    unavailable,

    /**
     * Missing user status.
     */
    missing,

    /**
     * Invalid user status.
     */
    invalid;

    /**
     * Value of user status.
     *
     * @param userStatus the userStatus
     *
     * @return the user status
     */
    public static ComicBookStatus valueOf(@IntRange(from = 0, to = 3) int userStatus) {
        if (userStatus == acquired.ordinal()) {
            return ComicBookStatus.acquired;
        } else if (userStatus == pending.ordinal()) {
            return ComicBookStatus.pending;
        } else if (userStatus == unavailable.ordinal()) {
            return ComicBookStatus.unavailable;
        } else if (userStatus == missing.ordinal()) {
            return ComicBookStatus.missing;
        } else {
            return ComicBookStatus.invalid;
        }
    }

    public static int getStatusColor(ComicBookStatus status) {
        switch (status) {
            case acquired:
                return App.getContext().getResources().getColor(R.color.status_acquired);
            case pending:
                return App.getContext().getResources().getColor(R.color.status_pending);
            case unavailable:
                return App.getContext().getResources().getColor(R.color.status_unavailable);
            case missing:
                return App.getContext().getResources().getColor(R.color.status_missing);
            default:
                return App.getContext().getResources().getColor(R.color.status_acquired);
        }
    }

    public static String getStatusText(ComicBookStatus status) {
        switch (status) {
            case acquired:
                return App.getContext().getResources().getString(R.string.status_acquired);
            case pending:
                return App.getContext().getResources().getString(R.string.status_pending);
            case unavailable:
                return App.getContext().getResources().getString(R.string.status_unavailable);
            case missing:
                return App.getContext().getResources().getString(R.string.status_missing);
            default:
                return App.getContext().getResources().getString(R.string.status_acquired);
        }
    }
}
