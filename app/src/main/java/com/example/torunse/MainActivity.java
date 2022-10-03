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
    Bitmap mBitmap;

    ActivityResultCallback callback = new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {

                Intent data = result.getData();



                FileOutputStream fOut = null;


                try { fOut = openFileOutput("Picture.png", Context.MODE_PRIVATE);


                    mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);


                    fOut.flush();


                    fOut.close();


                }


                catch (FileNotFoundException e)


                { e.printStackTrace();


                }


                Log.w("MainActivity", "Received " + login + " amount:"+amount);
            }
        }
    };


    ActivityResultLauncher <Intent> cameraResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            callback);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        File myImage = new File("Picture.png");
        if(myImage.exists()){ //load your profile picture
            Bitmap theImage = BitmapFactory.decodeFile("Picture.png");
            myImageView.setImageBitmap( theImage);
        }

        Log.e("MainActivity", "In onCreate()");
        //loads the XML file on Screen:

        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
       String initialLogin= prefs.getString("LoginName", "");//should be tttttttt from MyData.xml


binding.loginText.setText(initialLogin);//put the previous version on screen



        binding.button.setOnClickListener(click ->
                //do this when you click the button:
        {
            String userTyped = binding.loginText.getText().toString();
//save this for next time:
            SharedPreferences.Editor editor = prefs.edit(); //let you save info to disk
            editor.putString("LoginName", userTyped);
            editor.commit();//save the file

//data/data/packagename/shared_prefs

            Intent nextPage = new Intent(MainActivity.this, SecondActivity.class);
             nextPage.putExtra("LoginName", userTyped);//save what was typed under name "LoginName"
            nextPage.putExtra("Age", 25);


           cameraResult.launch(nextPage); //this will call onActivityResult above when it finishes()

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