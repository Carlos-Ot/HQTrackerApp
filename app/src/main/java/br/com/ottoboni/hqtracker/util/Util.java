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
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Util {

    // Sql comments begin with two consecutive "-" characters
    private static final String REG_COMMENT_EXPRESSION = "--";
    private static final String REG_EXPRESSION = ";";

    /**
     * Split in strings the content of sql files.
     *
     * @param context   the context
     * @param fileNames the file names
     *
     * @return string [ ]
     */
    public static String[] getStatementSql(Context context, final String fileNames) {
        final StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream;
        BufferedReader sqlFile = null;
        try {
            inputStream = context.getAssets().open(fileNames);
            final InputStreamReader reader = new InputStreamReader(inputStream,
                Charset.defaultCharset());
            sqlFile = new BufferedReader(reader);
            String buffer;
            while ((buffer = sqlFile.readLine()) != null) {
                //Ignore comment in sql
                if (!buffer.startsWith(REG_COMMENT_EXPRESSION)) {
                    stringBuilder.append(buffer);
                }
            }

        } catch (final IOException e) {
            //TODO: Replace with LogClass
            Log.d("util", "error");
        } finally {
            if (sqlFile != null) {
                try {
                    sqlFile.close();
                } catch (final IOException e) {
                    //TODO: Replace with LogClass
                    Log.d("util", "IOException");
                }
            }
        }

        return stringBuilder.toString().split(REG_EXPRESSION);
    }
}
