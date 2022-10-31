package com.example.torunse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.torunse.databinding.ActivityChatRoomBinding;
import com.example.torunse.databinding.RowLayoutBinding;

import java.util.ArrayList;

public class ChatRoom extends AppCompatActivity {

    ArrayList<String> allMessages = new ArrayList<>();

    ActivityChatRoomBinding binding;

    RecyclerView.Adapter<MyRowHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));//saying it's a vertical list

        binding.button2.setOnClickListener( click ->{

            String messageText= binding.textInput.getText().toString();
            allMessages.add(messageText);

            //refresh the list:
            adapter.notifyItemChanged(allMessages.size()-1); //wants to know which position has changed
            binding.textInput.setText("");//remove what was there
        });

                                                                //you must create this class below, extend RecyclerView.ViewHolder
        binding.recycleView.setAdapter(adapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override   //given the view type, just load a MyRowHolder      //what layout do you want?
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                 //line 1 is to inflate,
                View root ;

                 root = getLayoutInflater().inflate(R.layout.row_layout, parent, false);

             //line 2 is to pass the root to constructor
                return new MyRowHolder( root );

            }


            @Override
            public int getItemViewType(int position) {
                return 0; //0 represents send, text on the left
            }

            @Override                                               //position is the row, starting with 0
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                //here is where we set the textViews:
                //what goes in row at position?
                String thisRow = allMessages.get(position);// get what goes in this row

                //set the textView:
                holder.messageText.setText(thisRow);// that's all this does

            }

            @Override  // how many rows are there?
            public int getItemCount() {
                return allMessages.size();
            }
        });  //An adapter is an object that feeds data to the list
    }

//represents a single row
    class MyRowHolder extends RecyclerView.ViewHolder {

        TextView timeText;
        TextView messageText;

        //itemVIew is the ConstraintLayout that has 2 text subitems
        public MyRowHolder(@NonNull View itemView) {
            super(itemView);

            messageText = itemView.findViewById( R.id.messageId );//find the Message
            timeText = itemView.findViewById(R.id.timeId);//find the time
        }
    }
}