package com.example.torunse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/** @author Eric Torunski
 *  @version 1.0
 *  @since October 17, 2022
 */
public class MainActivity extends AppCompatActivity {

    /** This holds the login button reference   */
    protected Button lButton;

    /** This holds the editText reference for login*/

    protected EditText loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //loads the XML file on Screen:
        setContentView(R.layout.activity_main);
        lButton = findViewById(R.id.button2);
         loginText = findViewById(R.id.editText);

         TextView tv = findViewById(R.id.theText);

         lButton.setOnClickListener( click ->
                 tv.setText("You clicked the button")
                 );

        stringHas123(loginText.getText().toString());
    }


    /** This function checks if s has the string "123" somewhere in it
     *
     * @param s Is the string that we are checking for "123"
     * @return Returns true if s has "123", false otherwise
     */
    public boolean stringHas123(String s){
        return s.contains("123");
    }
}