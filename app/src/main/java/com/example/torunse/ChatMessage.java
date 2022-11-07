package com.example.torunse;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ChatMessage{

    @PrimaryKey (autoGenerate=true)
    @ColumnInfo(name="id")
    public int id;

    @ColumnInfo(name="MessageColumn")
    public String message;

    public ChatMessage(){    }

    public void setMessage(String s){message = s;}
    public String getMessage() {
        return message;
    }
}
