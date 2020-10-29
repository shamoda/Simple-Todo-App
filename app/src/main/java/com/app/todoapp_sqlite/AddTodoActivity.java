package com.app.todoapp_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddTodoActivity extends AppCompatActivity {

    private EditText title;
    private EditText desc;
    private Button add;
    private DBHelper dbHelper;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        title = findViewById(R.id.todo_title);
        desc = findViewById(R.id.todo_description);
        add = findViewById(R.id.add_todo_btn);
        context = this;

        dbHelper = new DBHelper(context);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String txtTitle = title.getText().toString();
                String txtDesc = desc.getText().toString();
                long started = System.currentTimeMillis();

                ToDo todo = new ToDo(txtTitle, txtDesc, started, 0);

                dbHelper.addTodo(todo);
                startActivity(new Intent(context, MainActivity.class));
                finish();
            }
        });

    }
}