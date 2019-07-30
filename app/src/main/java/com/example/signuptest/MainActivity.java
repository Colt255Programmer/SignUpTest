package com.example.signuptest;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;

import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText First,Last,Email,Password;

    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.INTERNET;
    private static final String ServerURL = "https://demonikhil.000webhostapp.com/get_Info.php";
// Volley Tutorial for GET AND POST https://www.kompulsa.com/how-to-send-a-get-request-in-android/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        btn = (Button) findViewById(R.id.btn);
        First = (EditText) findViewById(R.id.fname);
        Last = (EditText) findViewById(R.id.lname);
        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.pwd);

        btn.setEnabled(false);

        Password.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

                if (s.toString().equals("")) {
                    btn.setEnabled(false);
                } else {
                    btn.setEnabled(true);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertData(First.getText().toString(),
                        Last.getText().toString(),
                        Email.getText().toString(),
                        Password.getText().toString());

                Intent myIntent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }



    private void InsertData(final String f, final String l, final String e, final String p) {

        RequestQueue ExampleRequestQueue = Volley.newRequestQueue(this);

        String url = ServerURL+"?First="+f+"&Last="+l+"&Email="+e+"&Password="+p;
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                //You can test it by printing response.substring(0,500) to the screen.
                Toast.makeText(MainActivity.this, "Successfully Registered!", Toast.LENGTH_SHORT).show();
                Log.d("Response",response);
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
            }
        });

        ExampleRequestQueue.add(ExampleStringRequest);
    }
}
