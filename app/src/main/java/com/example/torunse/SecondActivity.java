package com.example.torunse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.torunse.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    ActivitySecondBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySecondBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        //get Intent object that has the variables sent

        Intent fromPrevious = getIntent();//will return the Intent with transition
        String loginName = fromPrevious.getStringExtra("LoginName");
        int age = fromPrevious.getIntExtra("Age", 0);

        binding.backButton.setText("Welcome " + loginName);


        Log.d("SecondActivity", "Found:" + loginName + " and " + age);//log information for debugging

        binding.backButton.setOnClickListener( click ->
                {
                    Intent dataToSend = new Intent();

                    dataToSend.putExtra("LoginName", "Eric");
                    dataToSend.putExtra("Amount", 100);

                    setResult(RESULT_OK, dataToSend); //go onActivityResult in MainActivity
                    finish();
                }
        );

                       //goes back to previous page


    }
}