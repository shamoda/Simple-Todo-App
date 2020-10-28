package com.app.todoapp_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditTodoActivity extends AppCompatActivity {

    private EditText title;
    private EditText desc;
    private Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);

        title = findViewById(R.id.edit_todo_title);
        desc = findViewById(R.id.edit_todo_description);
        edit = findViewById(R.id.edit_todo_btn);

    }
}