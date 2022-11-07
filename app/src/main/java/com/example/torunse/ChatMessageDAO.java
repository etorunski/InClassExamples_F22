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
    public void insertMessage(ChatRoom.ChatMessage m);

                        //This matches the @Entity class name
    @Query("Select * from ChatMessage;")
    public List<ChatRoom.ChatMessage> getAllMessages();

    @Delete
    public void deleteMessage(ChatRoom.ChatMessage m);
}
