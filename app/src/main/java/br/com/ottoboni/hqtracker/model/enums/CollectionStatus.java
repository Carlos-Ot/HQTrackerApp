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

import br.com.ottoboni.hqtracker.R;
import br.com.ottoboni.hqtracker.app.App;

public enum CollectionStatus {

    tracking,
    paused,
    finished,
    untracked,
    invalid;

    public static CollectionStatus valueOf(@IntRange(from = 0, to = 2) int collectionStatus) {
        if (collectionStatus == tracking.ordinal()) {
            return CollectionStatus.tracking;
        } else if (collectionStatus == paused.ordinal()) {
            return CollectionStatus.paused;
        } else if (collectionStatus == finished.ordinal()) {
            return CollectionStatus.finished;
        } else if (collectionStatus == untracked.ordinal()) {
            return CollectionStatus.untracked;
        } else {
            return CollectionStatus.invalid;
        }
    }

    public static String getStatusText(CollectionStatus status) {
        switch (status) {
            case tracking:
                return App.getContext().getResources().getString(R.string.status_tracking);
            case paused:
                return App.getContext().getResources().getString(R.string.status_paused);
            case finished:
                return App.getContext().getResources().getString(R.string.status_finished);
            case untracked:
                return App.getContext().getResources().getString(R.string.status_untracked);
            default:
                return App.getContext().getResources().getString(R.string.status_tracking);
        }
    }
}
