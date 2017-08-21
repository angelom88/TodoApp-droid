package com.ngelmat.simpletodo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ngelmat.simpletodo.R;
import com.ngelmat.simpletodo.adapter.TodoItemsAdapter;
import com.ngelmat.simpletodo.model.TodoItem;
import com.ngelmat.simpletodo.service.TodoService;
import com.ngelmat.simpletodo.service.impl.TodoServiceImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvItems;
    private List<TodoItem> items;
    private EditText etNewItem;
    private static final int EDIT_REQUEST_CODE = 1;
    private TodoService todoService;
    private TodoItemsAdapter todoItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItems = (ListView) findViewById(R.id.lvItems);
        todoService = TodoServiceImpl.create();
        items =  todoService.selectAll();
        todoItemsAdapter = new TodoItemsAdapter(this, items);

        lvItems.setAdapter(todoItemsAdapter);
        etNewItem = (EditText) findViewById(R.id.etNewItem);
        setupListViewListener();
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int pos, long id) {
                TodoItem todoItem = items.get(pos);
                todoService.delete(todoItem);
                loadItems();

                return true;
            }
        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                TodoItem editItem = items.get(pos);
                Intent editIntent = new Intent(MainActivity.this, EditItemActivity.class);
                editIntent.putExtra("edit_item", editItem);
                startActivityForResult(editIntent, EDIT_REQUEST_CODE);
            }
        });
    }

    public void onAddItem(View view) {
        String itemText = etNewItem.getText().toString().trim();
        if (!itemText.isEmpty()) {
            TodoItem todoItem = TodoItem.create(itemText);
            todoService.insert(todoItem);
            etNewItem.setText("");
            loadItems();

            Toast.makeText(this, "Successfully added", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (RESULT_OK == resultCode && EDIT_REQUEST_CODE == requestCode) {
            loadItems();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void loadItems() {
        items.clear();
        items.addAll(todoService.selectAll());
        todoItemsAdapter.notifyDataSetChanged();
    }

}
