package com.example.torunse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.torunse.data.MainActivityViewModel;
import com.example.torunse.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding variableBinding;

    MainActivityViewModel theViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        theViewModel =  new ViewModelProvider(this).get(MainActivityViewModel.class);

        variableBinding = ActivityMainBinding.inflate( getLayoutInflater() ); //will load my premade variables from ViewBinding

        variableBinding.checkBox.setText("hello");

 //onCheckedChange() function:
        variableBinding.checkBox.setOnCheckedChangeListener(   (button, isChecked) -> {
            variableBinding.checkBox.setText("You are checked:" + isChecked);
            theViewModel.isOn = isChecked;

        } );


        variableBinding.radioButton.setOnCheckedChangeListener(   (button, isChecked) -> {
            variableBinding.checkBox.setText("You are checked:" + isChecked);
            theViewModel.isOn = isChecked;
        } );

        variableBinding.switch1.setOnCheckedChangeListener(   (button, isChecked) -> {
            variableBinding.switch1.setText("You are checked:" + isChecked);
            theViewModel.isOn = isChecked;

        } );


        //observe the viewModel for changes:
        theViewModel.

        setContentView( variableBinding.getRoot()); //this shows the objects on screen
    }
}