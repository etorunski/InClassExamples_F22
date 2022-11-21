package com.example.torunse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.torunse.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.net.URLEncoder;


/** @author Eric Torunski
 *  @version 1.0
 *  @since October 17, 2022
 */
public class MainActivity extends AppCompatActivity {


    protected String cityName;
    protected RequestQueue queue = null;
    String iconName = null;
    ImageRequest imgReq;
    Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        queue = Volley.newRequestQueue(this);
        ActivityMainBinding binding = ActivityMainBinding.inflate( getLayoutInflater() );
        setContentView(binding.getRoot());


        binding.getForecast.setOnClickListener(click -> {
            cityName = binding.editText.getText().toString();
            String stringURL = "https://api.openweathermap.org/data/2.5/weather?q=" + URLEncoder.encode(cityName) + "&appid=7e943c97096a9784391a981c4d878b22&units=metric";

            //this goes in the button click handler:
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, stringURL, null,
                    (response) -> {

                        try {
                            JSONObject coord = response.getJSONObject( "coord" );
                           iconName = response.getJSONArray("weather").getJSONObject(0).getString("icon");


                           String pathname = getFilesDir() + "/" + iconName + ".png";
                           File file = new File(pathname);
                           if(file.exists())
                           {
                               image = BitmapFactory.decodeFile(pathname);
                           }
                           else {
                               imgReq = new ImageRequest("https://openweathermap.org/img/w/" + iconName + ".png", new Response.Listener<Bitmap>() {
                                   @Override
                                   public void onResponse(Bitmap bitmap) {
                                       try {
                                           // Do something with loaded bitmap...
                                           image = bitmap;
                                           image.compress(Bitmap.CompressFormat.PNG, 100, MainActivity.this.openFileOutput(iconName + ".png", Activity.MODE_PRIVATE));
                                       }
                                       catch(Exception e){

                                       }
                                   }
                               }, 1024, 1024, ImageView.ScaleType.CENTER, null, (error) -> {   });
                           }


                            String current, minTemp, maxTemp;
                            current = minTemp = maxTemp = "";


                            binding.temp.setText("The current temperature is " + current);
                            binding.temp.setVisibility(View.VISIBLE);

                            binding.minTemp.setText("The min temperature is " + minTemp);
                            binding.minTemp.setVisibility(View.VISIBLE);


                            queue.add(imgReq);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    },
                    (error) -> {
                Log.e("Error", "error");
                    } );
            queue.add(request);
        });
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