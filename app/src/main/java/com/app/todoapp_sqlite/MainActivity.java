package com.app.todoapp_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView list;
    private TextView count;
    private Button add;
    Context context;
    private DBHelper dbHelper;
    private List<ToDo> toDos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.list_view);
        count = findViewById(R.id.no_of_todos);
        add = findViewById(R.id.add_todo);
        context = this;
        dbHelper = new DBHelper(context);
        toDos = new ArrayList<>();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, AddTodoActivity.class));
            }
        });

        count.setText("You have " +dbHelper.countTodo()+ " Todos");

        toDos = dbHelper.getAllTodos();
        TodoAdapter todoAdapter = new TodoAdapter(context, R.layout.single_todo, toDos);
        list.setAdapter(todoAdapter);

    }
}