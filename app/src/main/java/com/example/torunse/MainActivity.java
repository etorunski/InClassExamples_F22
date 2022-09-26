package com.example.torunse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.torunse.data.MainActivityViewModel;
import com.example.torunse.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding variableBinding;

    MainActivityViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model =  new ViewModelProvider(this).get(MainActivityViewModel.class);

        variableBinding = ActivityMainBinding.inflate( getLayoutInflater() ); //will load my premade variables from ViewBinding

        variableBinding.checkBox.setOnCheckedChangeListener(   (button, isChecked) -> {
            model.isSelected.postValue(isChecked);
        } );

        variableBinding.radioButton.setOnCheckedChangeListener(   (button, isChecked) -> {
            model.isSelected.postValue(isChecked);
        } );

        variableBinding.switch1.setOnCheckedChangeListener(   (button, isChecked) -> {
            model.isSelected.postValue(isChecked);
        } );

        variableBinding.anImage.setOnClickListener(click ->
                Toast.makeText(this, "You clicked on the image", Toast.LENGTH_LONG).show()
                );


        model.isSelected.observe(this, selected -> {
                    variableBinding.checkBox.setChecked(selected);
                    variableBinding.radioButton.setChecked(selected);
                    variableBinding.switch1.setChecked(selected);


            Context context = getApplicationContext();
            CharSequence text = "You clicked the value and it is now:" +selected;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
                });

        setContentView( variableBinding.getRoot()); //this shows the objects on screen
    }
}