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
import android.graphics.Typeface;

public class FontUtil {

    public static Typeface ROBOTO_MEDIUM;
    public static Typeface ROBOTO_REGULAR;

    public static void loadFonts(Context context) {
        ROBOTO_MEDIUM = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Medium.ttf");
        ROBOTO_REGULAR = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
    }
}
