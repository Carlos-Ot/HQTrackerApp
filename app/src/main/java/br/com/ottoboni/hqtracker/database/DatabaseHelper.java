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

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Locale;

import br.com.ottoboni.hqtracker.app.App;
import br.com.ottoboni.hqtracker.util.Util;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_SCRIPT = "db_create_script.sql";
    private static final String SQL_UPDATE_SCRIPT_MASK = "db_update_v%d_to_v%d.sql";
    private static final String DATABASE_NAME = "db_hq_tracker";
    private static final int DB_VERSION = 1;
    private static final int FOR_STEP = 1;

    public DatabaseHelper() {
        super(App.getContext(), DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        executeSqlCommand(db, SQL_CREATE_SCRIPT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (int i = oldVersion; i < newVersion; ++i) {
            String script = String.format(Locale.US, SQL_UPDATE_SCRIPT_MASK, i, (i + FOR_STEP));
            executeSqlCommand(db, script);
        }
    }

    /**
     * Use to execute all sql instructions in sqls into the assets folder
     *
     * @param sqLiteDatabase the instance SQLite Database
     * @param sqlFileName    the SQL filename
     */
    private void executeSqlCommand(SQLiteDatabase sqLiteDatabase, String sqlFileName) {
        try {
            //Get all sql instructions from the SQL_NAME_ASSETS file.
            String[] sqlInstructions = Util.getStatementSql(App.getContext(), sqlFileName);

            for (final String sql : sqlInstructions) {
                if (sql != null && !sql.isEmpty()) {
                    sqLiteDatabase.execSQL(sql);
                }
            }
        } catch (IllegalArgumentException e) {
            //TODO: Replace with log class
        }
    }
}
