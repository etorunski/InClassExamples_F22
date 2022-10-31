package com.example.torunse;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import com.example.torunse.databinding.ActivityMainBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.button.setOnClickListener(click ->
                //do this when you click the button:
        {
            Intent nextPage = new Intent(MainActivity.this, ChatRoom.class);
            startActivity(nextPage); //this will call onActivityResult above when it finishes()
        });
    }


    //activity is visible, but not responding to touch
    @Override
    protected void onStart() {
        super.onStart();

        Log.e("MainActivity", "In onStart()");
    }

    //activity is visible, and responding to touch
    @Override
    protected void onResume() {
        super.onResume();
        Log.w("MainActivity", "In onResume()");
    }


    //activity is visible, and not responding to touch
    @Override
    protected void onPause() {
        super.onPause();
        Log.e("MainActivity", "In onPause()");
    }

    //no longer visible
    @Override
    protected void onStop() {
        super.onStop();
        Log.e("MainActivity", "In onStop()");
    }

    //the garbage collector is clearing the memory
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}