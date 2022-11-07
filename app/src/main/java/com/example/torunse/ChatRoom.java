package com.example.torunse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.torunse.data.ChatRoomViewModel;
import com.example.torunse.data.MainActivityViewModel;
import com.example.torunse.databinding.ActivityChatRoomBinding;
import com.example.torunse.databinding.SentMessageBinding;
import com.google.android.material.snackbar.Snackbar;

import java.sql.Array;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ChatRoom extends AppCompatActivity {



    ActivityChatRoomBinding binding;
    ChatRoomViewModel chatModel;
    ArrayList<ChatMessage> messages;

    private RecyclerView.Adapter<MyRowHolder> myAdapter;
    ChatMessageDAO mDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);

        messages = chatModel.messages.getValue();

        //load from the database:
        MessageDatabase db = Room.databaseBuilder(getApplicationContext(), MessageDatabase.class, "MessageDatabase").build();
        mDAO = db.cmDAO();

        if(messages == null)
        {
            chatModel.messages.postValue(messages = new ArrayList<>());

            //load everything:
            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute( () -> {
                //whatever is in here runs on another processor.

                messages.addAll( mDAO.getAllMessages() );
                //now you can load the RecyclerVIew:

                runOnUiThread(() ->{
                    binding.recycleView.setAdapter( myAdapter );
                });

            }  );
        }

        binding.sendButton.setOnClickListener(click -> {

            ChatMessage newMessage = new ChatMessage();
            newMessage.setMessage( binding.textInput.getText().toString() );

            chatModel.messages.getValue().add(newMessage);

            myAdapter.notifyItemInserted( messages.size()-1 );
            //clear the previous text:
            binding.textInput.setText("");

            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute( () -> {
                mDAO.insertMessage(newMessage);
            });
        });

        //Set a layout manager for the rows to be aligned vertically using only 1 column.
        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater());
                return new MyRowHolder(  binding.getRoot() );
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                holder.messageText.setText(messages.get(position).getMessage() );
                holder.timeText.setText("");
            }

            @Override
            public int getItemCount() {
                return messages.size();
            }

            @Override
            public int getItemViewType(int position) {
                return 0;
            }
        };
    }

    class MyRowHolder extends RecyclerView.ViewHolder {
        TextView messageText;
        TextView timeText;

        public MyRowHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(click ->{

                //which row was click
                int position = getAdapterPosition();
               ChatMessage thisMessage = messages.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder( ChatRoom.this );
                builder.setMessage(  thisMessage.message );

                builder.setTitle("Do you want to delete this? ");

                builder.setNegativeButton("No", (a, b)->{   });
                builder.setPositiveButton("Yes", (a, b)->{

                    Snackbar.make( messageText, "You deleted position #" + position, Snackbar.LENGTH_LONG)
                            .setAction( "Undo", click1-> {

                                Executor thread = Executors.newSingleThreadExecutor();
                                thread.execute( () -> {
                                    mDAO.insertMessage(thisMessage);
                                });
                                chatModel.messages.getValue().add(thisMessage);
                                myAdapter.notifyItemInserted( position );

                            } )  .show();

                    Executor thread = Executors.newSingleThreadExecutor();
                    thread.execute( () -> {
                        mDAO.deleteMessage(thisMessage);
                    });

                    myAdapter.notifyItemRemoved( position );
                    chatModel.messages.getValue().remove(position);

                });
                builder.create().show();
            });

            messageText = itemView.findViewById(R.id.messageText);
            timeText = itemView.findViewById(R.id.timeText);
        }
    }


}