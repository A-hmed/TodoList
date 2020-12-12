package com.example.cs426task2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class todoListAdapter extends RecyclerView.Adapter<todoListAdapter.ViewHolder> {

    ArrayList<TodoItem> list;

    public todoListAdapter(ArrayList<TodoItem> list) {
        this.list = list;
    }

    OnItemClickListner onItemClickListner = null;

    public void setOnItemClickListner(OnItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final TodoItem todoItem = list.get(position);
        holder.Title.setText(todoItem.Title);
        holder.Description.setText(todoItem.Description);
        holder.isCompleted.setChecked(todoItem.isCompleted);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListner != null) {
                    onItemClickListner.onItemClickListner(todoItem, position);
                }
            }
        });
        holder.isCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListner != null) {
                    onItemClickListner.onChekcBoxListner(todoItem, position, view);
                }
            }
        });
       holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View view) {
               if (onItemClickListner != null) {
                   onItemClickListner.onItemLongClick(todoItem, position);
               }
               return false;
           }
       });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    interface OnItemClickListner {
        void onItemClickListner(TodoItem todoItem, int position);

        void onChekcBoxListner(TodoItem todoItem, int position,View view);

        void onItemLongClick(TodoItem todoItem, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView Title, Description;
        CheckBox isCompleted;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.todoTitle);
            Description = itemView.findViewById(R.id.todoDescription);
            isCompleted = itemView.findViewById(R.id.todoIsCompleted);

        }
    }
}
