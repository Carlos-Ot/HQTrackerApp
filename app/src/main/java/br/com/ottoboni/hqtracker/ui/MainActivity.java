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

package br.com.ottoboni.hqtracker.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

import br.com.ottoboni.hqtracker.R;
import br.com.ottoboni.hqtracker.app.App;
import br.com.ottoboni.hqtracker.controllers.CommunicationController;
import br.com.ottoboni.hqtracker.model.enums.HttpStatus;
import br.com.ottoboni.hqtracker.util.PreferencesManager;

public class MainActivity extends AppCompatActivity {

    private static final int COUNT_DOWN_INTERVAL = 2000;
    private static final int MILLIS_IN_FUTURE = 9000;
    private static final int MILLIS_IN_FUTURE_TASK = 3000;
    private ProgressBar progressBar;
    private TextView txtProgress;

    private String[] txtProgressArray;
    private Random random = new Random();
    private int randomIndex;

    private RequestDataTask requestDataTask;

    private CountDownTimer mProgressTimer = new CountDownTimer(MILLIS_IN_FUTURE, COUNT_DOWN_INTERVAL) {
        @Override
        public void onTick(long millisUntilFinished) {
            randomIndex = random.nextInt(txtProgressArray.length);
            txtProgress.setText(txtProgressArray[randomIndex]);
        }

        @Override
        public void onFinish() {
        }
    };

    private CountDownTimer mCallHomeActivityTimer = new CountDownTimer(MILLIS_IN_FUTURE_TASK, COUNT_DOWN_INTERVAL) {
        @Override
        public void onTick(long millisUntilFinished) {
            randomIndex = random.nextInt(txtProgressArray.length);
            txtProgress.setText(txtProgressArray[randomIndex]);
        }

        @Override
        public void onFinish() {
            txtProgress.setText(R.string.txt_end_progress);

            Intent intent = new Intent(App.getContext(), HomeActivity.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        txtProgress = (TextView) findViewById(R.id.txt_progress);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();


        txtProgressArray = App.getContext().getResources().getStringArray(R.array.progress_text);

        if (PreferencesManager.isFirstAccess()) {
            mProgressTimer.start();
            requestDataTask = new RequestDataTask();
            requestDataTask.execute();

            PreferencesManager.setFirstAccess(false);
        }
        else {
            mProgressTimer.onFinish();
            mCallHomeActivityTimer.start();
        }
    }

    private class RequestDataTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            HttpStatus collections = null;
            HttpStatus comicBooks = null;
            boolean result = false;

            collections = CommunicationController.getInstance().requestCollections();

            if (collections == HttpStatus.SUCCESS)
            {
                comicBooks = CommunicationController.getInstance().requestComicBookList();
                if (comicBooks == HttpStatus.SUCCESS)
                {
                    result = true;
                }
            }
            else {
                result = false;
            }

            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            if (result) {
                mCallHomeActivityTimer.start();
            }

        }
    }
}
