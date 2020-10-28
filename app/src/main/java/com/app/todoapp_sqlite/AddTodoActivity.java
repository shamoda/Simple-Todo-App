package com.app.todoapp_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddTodoActivity extends AppCompatActivity {

    private EditText title;
    private EditText desc;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        title = findViewById(R.id.todo_title);
        desc = findViewById(R.id.todo_description);
        add = findViewById(R.id.add_todo_btn);

    }
}