package com.example.cs426task2;

import java.io.Serializable;

public class TodoItem implements Serializable {
    String Title,Description;
    Boolean isCompleted;

    public TodoItem(String title, String description, Boolean isCompleted) {
        Title = title;
        Description = description;
        this.isCompleted = isCompleted;
    }

}
