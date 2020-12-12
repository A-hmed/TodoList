package com.example.cs426task2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<TodoItem> list = new ArrayList<TodoItem>();
    int LAUNCH_INFO_ACTIVITY = 1;
    todoListAdapter adapter;
    AlertDialog.Builder mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.todoRecyclerView);
        fillList();

        adapter = new todoListAdapter(list);
        adapter.setOnItemClickListner(new todoListAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(TodoItem todoItem, int position) {
                Intent intent = new Intent(getBaseContext(), InfoActivity.class);
                intent.putExtra("todoItem", todoItem);
                intent.putExtra("position", position);
                startActivityForResult(intent, LAUNCH_INFO_ACTIVITY);
            }

            @Override
            public void onChekcBoxListner(TodoItem todoItem, int position, View view) {
                list.set(position, new TodoItem(todoItem.Title, todoItem.Description, !todoItem.isCompleted));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onItemLongClick(TodoItem todoItem, int position) {
                final int position1 = position;
                mProgressDialog = new AlertDialog.Builder(MainActivity.this);

                //  mProgressDialog.setIndeterminate(true);
                 //mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.setTitle("Delete");
                mProgressDialog.setMessage("Are you sure you want delete this item");
                mProgressDialog.setPositiveButton(
                        "yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                //Do Something Here
                                list.remove(position1);
                                adapter.notifyDataSetChanged();
                            }
                        });
                mProgressDialog.show();

            }
        });
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LAUNCH_INFO_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                TodoItem todoItem = (TodoItem) data.getSerializableExtra("todoItem");
                Log.e("result", " boolean is" + todoItem.Title);
                int position = data.getIntExtra("position", -1);
                list.set(position, new TodoItem(todoItem.Title, todoItem.Description, todoItem.isCompleted));
                adapter.notifyDataSetChanged();
            }

        }
    }

    void fillList() {
        for (int i = 0; i < 30; i++) {
            list.add(new TodoItem("Title " + i, "Description " + i, false));
        }
    }

}

/*

new todoListAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(TodoItem todoItem, int position) {
              Intent intent=new Intent(getBaseContext(),InfoActivity.class);
              intent.putExtra("todoItem",todoItem);
              intent.putExtra("position",position);
                startActivityForResult(intent, LAUNCH_INFO_ACTIVITY);

            }
            @Override
            public void onChekcBoxListner(TodoItem todoItem, int position, View view) {
              //  CheckBox checkBox=(CheckBox)view;

                list.set(position,new TodoItem(todoItem.Title,todoItem.Description,!todoItem.isCompleted));
                        adapter.notifyDataSetChanged();
                        }
@Override
        }
 */