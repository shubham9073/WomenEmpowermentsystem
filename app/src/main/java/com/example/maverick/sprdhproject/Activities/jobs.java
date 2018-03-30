package com.example.maverick.sprdhproject.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.maverick.sprdhproject.R;

public class jobs extends AppCompatActivity {
    Button jo1,jo2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);
        jo1=(Button) findViewById(R.id.jo1);
        jo2=(Button) findViewById(R.id.jo2);

        jo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://www.naukri.com/women-housekeeping-jobs-in-mumbai";
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);
            }
        });

        jo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://www.naukri.com/women-jobs-in-mumbai";
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);
            }
        });



    }
}
