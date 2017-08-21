package com.ngelmat.simpletodo.dao.table;


import com.ngelmat.simpletodo.dao.TodoDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.UUID;

@Table(database = TodoDatabase.class)
public class TodoItemTable extends BaseModel {

    @Column
    @PrimaryKey
    private UUID id;

    @Column
    private String item;

    public TodoItemTable() {
    }

    public TodoItemTable(UUID id, String item) {
        this.id = id;
        this.item = item;
    }

    public TodoItemTable(String item) {
        this.id = UUID.randomUUID();
        this.item = item;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
