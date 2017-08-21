package com.ngelmat.simpletodo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ngelmat.simpletodo.R;
import com.ngelmat.simpletodo.model.TodoItem;

import java.util.List;

public class TodoItemsAdapter extends ArrayAdapter<TodoItem> {
    public TodoItemsAdapter(Context context, List<TodoItem> todoItems) {
        super(context, 0, todoItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TodoItem todoItem = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_todo, parent, false);
        }

        TextView tvTodoItem = (TextView) convertView.findViewById(R.id.tvTodoItem);
        tvTodoItem.setText(todoItem.getItem());

        return convertView;
    }
}
