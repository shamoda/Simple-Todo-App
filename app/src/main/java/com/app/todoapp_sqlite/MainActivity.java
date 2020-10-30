package com.app.todoapp_sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final ToDo toDo = toDos.get(i);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(toDo.getTitle());
                builder.setMessage(toDo.getDescription());
                builder.setPositiveButton("Finished", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        toDo.setFinished(System.currentTimeMillis());
                        dbHelper.updateTodo(toDo);
                        startActivity(new Intent(context, MainActivity.class));
                    }
                });
                builder.setNegativeButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context, EditTodoActivity.class);
                        intent.putExtra("id", String.valueOf(toDo.getId()));
                        startActivity(intent);
                    }
                });

                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHelper.deleteTodo(toDo.getId());
                        startActivity(new Intent(context, MainActivity.class));
                    }
                });

                builder.show();
            }
        });

    }
}