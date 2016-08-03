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

package br.com.ottoboni.hqtracker.util;

import android.content.Context;
import android.content.SharedPreferences;

import br.com.ottoboni.hqtracker.app.App;

public class PreferencesManager {

    private static final String PREFERENCE_NAME = "hq_tracker_preference";

    private static final String KEY_FIRST_ACCESS = "first_access";

    private static PreferencesManager mInstance;

    public PreferencesManager() {}

    public static PreferencesManager getInstance() {
        if (mInstance == null) {
            mInstance = new PreferencesManager();
        }

        return mInstance;
    }

    private static SharedPreferences getPreferences() {
        return App.getContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public static boolean isFirstAccess() {
        return getPreferences().getBoolean(KEY_FIRST_ACCESS, true);
    }

    public static void setFirstAccess(boolean firstAccess) {
        getPreferences().edit().putBoolean(KEY_FIRST_ACCESS, firstAccess).apply();
    }

}
