/*
* Copyright (C) 2016 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.todolist;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import static android.text.TextUtils.isEmpty;
import static android.widget.Toast.LENGTH_LONG;
import static com.example.android.todolist.data.TaskContract.TaskEntry.COLUMN_DESCRIPTION;
import static com.example.android.todolist.data.TaskContract.TaskEntry.COLUMN_PRIORITY;
import static com.example.android.todolist.data.TaskContract.TaskEntry.CONTENT_URI;


public class AddTaskActivity extends AppCompatActivity {

    // Declare a member variable to keep track of a task's selected mPriority
    private int mPriority;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        // Initialize to highest mPriority by default (mPriority = 1)
        ((RadioButton) findViewById(R.id.radButton1)).setChecked(true);
        mPriority = 1;
    }


    /**
     * onClickAddTask is called when the "ADD" button is clicked.
     * It retrieves user input and inserts that new task data into the underlying database.
     */
    public void onClickAddTask(View view) {
        // Not yet implemented
        String taskDescription = ((EditText) findViewById(R.id.editTextTaskDescription)).getText().toString();
        if (isEmpty(taskDescription)) {
            return;
        }

        ContentValues contentValues  = new ContentValues();
        contentValues.put(COLUMN_DESCRIPTION, taskDescription);
        contentValues.put(COLUMN_PRIORITY, mPriority);
        Uri uri = getContentResolver().insert(CONTENT_URI, contentValues);

        if (uri != null) {
            Toast.makeText(this, uri.toString(), LENGTH_LONG).show();
        }

        finish();
        // [Hint] Don't forget to call finish() to return to MainActivity after this insert is complete

    }


    /**
     * onPrioritySelected is called whenever a priority button is clicked.
     * It changes the value of mPriority based on the selected button.
     */
    public void onPrioritySelected(View view) {
        if (((RadioButton) findViewById(R.id.radButton1)).isChecked()) {
            mPriority = 1;
        } else if (((RadioButton) findViewById(R.id.radButton2)).isChecked()) {
            mPriority = 2;
        } else if (((RadioButton) findViewById(R.id.radButton3)).isChecked()) {
            mPriority = 3;
        }
    }
}
