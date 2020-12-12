package com.example.cs426task2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {
    TextView Title, Description;
    CheckBox isCompleted;
    Button back;
    TodoItem todoItem;
    int position;
    Boolean checkBoxValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Title = findViewById(R.id.todoTitleInfo);
        Description = findViewById(R.id.todoDescriptionInfo);
        isCompleted = findViewById(R.id.todoIsCompletedInfo);
        back = findViewById(R.id.backButton);
        todoItem = (TodoItem) getIntent().getSerializableExtra("todoItem");
        position = getIntent().getIntExtra("position", -1);
        Title.setText(todoItem.Title);
        Description.setText(todoItem.Description);
        isCompleted.setChecked(todoItem.isCompleted);
        checkBoxValue = isCompleted.isChecked();
        isCompleted.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               Log.e("result","check box value is "+checkBoxValue);
                                               checkBoxValue = isCompleted.isChecked();
                                           }
                                       });
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("todoItem",new TodoItem(todoItem.Title,todoItem.Description,checkBoxValue));
                        returnIntent.putExtra("position", position);
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                    }
                });
    }


    interface onChange {
        void onChange(TodoItem todoItem, int position);
    }

}