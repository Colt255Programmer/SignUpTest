package com.example.signuptest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class RegisterActivity extends Activity {

    private LinearLayout parentLinearLayout;
    EditText number_edit_text;
    String temp="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        number_edit_text = (EditText) findViewById(R.id.number_edit_text);
        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);

    }

    public void onAddField(View v) {
        //LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //fina1l View rowView = inflater.inflate(R.layout.field, null);
        // Add the new row before the add field button.
        final Button btn;
        btn = (Button) findViewById(R.id.add_field_button);
        number_edit_text = (EditText) findViewById(R.id.number_edit_text);
        btn.setEnabled(false);
        Intent myIntent = getIntent();
        String str = myIntent.getStringExtra("Email");
        //text.setText(str);

        number_edit_text.addTextChangedListener(new TextWatcher() {

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

        //Toast.makeText(RegisterActivity.this,"Enter Lecture Name",Toast.LENGTH_LONG).show();
        temp = temp + number_edit_text.getText().toString();
        Log.d("Temp : ",temp);
        //Log.d("Email: ",str);
        number_edit_text.setText("");
        Toast.makeText(RegisterActivity.this,"Lecture Added",Toast.LENGTH_LONG).show();
        LectureData(str,temp);
        if(temp=="" || temp==" ")
        { }
        else
        { temp = temp + ","; }

        //parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
    }

    public void onDelete(View v) {
        parentLinearLayout.removeView((View) v.getParent());
    }

    private void LectureData(final String Email, final String Lecture) {

        RequestQueue ExampleRequestQueue = Volley.newRequestQueue(this);

        //https://demonikhil.000webhostapp.com/add_Lecture.php?Email=nikhilnich@gmail.com&Lectures=12345678
        String ServerURL = "https://demonikhil.000webhostapp.com/add_Lecture.php?";
        String url = ServerURL+"Email="+Email+"&Lectures="+Lecture;
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                //You can test it by printing response.substring(0,500) to the screen.
                Toast.makeText(RegisterActivity.this, "Lecture Added!", Toast.LENGTH_SHORT).show();
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