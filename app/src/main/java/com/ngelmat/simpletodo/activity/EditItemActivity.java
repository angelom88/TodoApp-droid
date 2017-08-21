package com.ngelmat.simpletodo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ngelmat.simpletodo.R;
import com.ngelmat.simpletodo.model.TodoItem;
import com.ngelmat.simpletodo.service.TodoService;
import com.ngelmat.simpletodo.service.impl.TodoServiceImpl;

public class EditItemActivity extends AppCompatActivity {

    private EditText etEditItem;
    private TodoItem editItem;

    private TodoService todoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        todoService = new TodoServiceImpl();

        etEditItem = (EditText) findViewById(R.id.etEditItem);
        editItem = getIntent().getParcelableExtra("edit_item");
        etEditItem.setText(editItem.getItem());
    }


    public void onSubmitSave(View view) {
        Intent editedData = new Intent();
        String editedItem = etEditItem.getText().toString().trim();
        editedData.putExtra("editted_item", editedItem);
        editItem.setItem(editedItem);
        todoService.update(editItem);
        setResult(RESULT_OK, editedData);
        Toast.makeText(this, "Successfully updated", Toast.LENGTH_SHORT).show();
        finish();
    }
}
