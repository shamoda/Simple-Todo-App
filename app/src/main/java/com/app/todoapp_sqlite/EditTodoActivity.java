package com.app.todoapp_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditTodoActivity extends AppCompatActivity {

    private EditText title;
    private EditText desc;
    private Button edit;
    private DBHelper dbHelper;
    private Context context;
    private Long updateDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");

        title = findViewById(R.id.edit_todo_title);
        desc = findViewById(R.id.edit_todo_description);
        edit = findViewById(R.id.edit_todo_btn);
        context = this;
        dbHelper = new DBHelper(context);

        ToDo toDo = dbHelper.getTodo(Integer.parseInt(id));

        title.setText(toDo.getTitle());
        desc.setText(toDo.getDescription());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtTitle = title.getText().toString();
                String txtDesc = desc.getText().toString();
                updateDate = System.currentTimeMillis();

                ToDo toDo1 = new ToDo(Integer.parseInt(id), txtTitle, txtDesc, updateDate,0);
                int status = dbHelper.updateTodo(toDo1);
                Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context, MainActivity.class));
            }
        });

    }
}