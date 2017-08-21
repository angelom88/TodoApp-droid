package com.ngelmat.simpletodo.service.impl;

import com.ngelmat.simpletodo.dao.table.TodoItemTable;
import com.ngelmat.simpletodo.model.TodoItem;
import com.ngelmat.simpletodo.service.TodoService;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

public class TodoServiceImpl implements TodoService {

    public static TodoServiceImpl create() {
        return new TodoServiceImpl();
    }

    @Override
    public void insert(TodoItem todoItem) {
        TodoItemTable todoItemTable = new TodoItemTable(todoItem.getItem());
        todoItemTable.insert();
    }

    @Override
    public void update(TodoItem todoItem) {
        TodoItemTable todoItemTable = new TodoItemTable(todoItem.getId(), todoItem.getItem());
        todoItemTable.update();
    }

    @Override
    public void delete(TodoItem todoItem) {
        TodoItemTable todoItemTable = new TodoItemTable(todoItem.getId(), todoItem.getItem());
        todoItemTable.delete();
    }

    @Override
    public List<TodoItem> selectAll() {
        List<TodoItem> todoItems = new ArrayList<>();
        for (TodoItemTable row: SQLite.select().from(TodoItemTable.class).queryList()) {
            todoItems.add(new TodoItem(row.getId(), row.getItem()));
        }

        return todoItems;
    }
}
