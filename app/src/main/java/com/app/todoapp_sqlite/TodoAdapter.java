package com.app.todoapp_sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TodoAdapter extends ArrayAdapter<ToDo> {

    private Context context;
    private int resource;
    private List<ToDo> toDos;

    TodoAdapter(Context context, int resource, List<ToDo> toDos){
        super(context, resource, toDos);

        this.context = context;
        this.resource = resource;
        this.toDos = toDos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource, parent, false);

        TextView title = row.findViewById(R.id.row_title);
        TextView description = row.findViewById(R.id.row_description);
        ImageView done = row.findViewById(R.id.row_done);

        ToDo toDo = toDos.get(position);
        title.setText(toDo.getTitle());
        description.setText(toDo.getDescription());
        done.setVisibility(View.INVISIBLE);

        if (toDo.getFinished() > 0){
            done.setVisibility(View.VISIBLE);
        }
        return row;
    }
}
