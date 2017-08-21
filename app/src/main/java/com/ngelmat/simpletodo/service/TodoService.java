package com.ngelmat.simpletodo.service;

import com.ngelmat.simpletodo.model.TodoItem;

import java.util.List;

public interface TodoService {

    void insert(TodoItem todoItem);
    void update(TodoItem todoItem);
    void delete(TodoItem todoItem);
    List<TodoItem> selectAll();
}
