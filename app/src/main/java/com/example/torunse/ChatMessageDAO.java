package com.example.torunse;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Dao;
import java.util.List;

//object that performs CRUD operations
@Dao
public interface ChatMessageDAO {

    @Insert
    public long insertMessage(ChatMessage m);

                        //This matches the @Entity class name
    @Query("Select * from ChatMessage;")
    public List<ChatMessage> getAllMessages();

    @Delete
    public void deleteMessage(ChatMessage m);
}
