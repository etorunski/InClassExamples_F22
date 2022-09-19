package com.example.torunse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.torunse.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding variableBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        variableBinding = ActivityMainBinding.inflate( getLayoutInflater() ); //will load my premade variables from ViewBinding


//only 1 function can use Lambda functions
        variableBinding.firstButton.setOnClickListener(   ( v ) -> {
            variableBinding.firstString.setText("You clicked on the button");
            variableBinding.secondString.setText("You clicked on the button");
        }  ); //onClick function based on looking up documentation


        setContentView( variableBinding.getRoot()); //this shows the objects on screen
    }
}