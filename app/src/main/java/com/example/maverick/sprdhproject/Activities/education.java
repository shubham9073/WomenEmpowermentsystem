package com.example.maverick.sprdhproject.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.maverick.sprdhproject.R;
public class education extends AppCompatActivity {
    Button ed1,ed2,ed3,ed4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);
        ed1=(Button) findViewById(R.id.ed1);
        ed2=(Button) findViewById(R.id.ed2);
        ed3=(Button) findViewById(R.id.ed3);
        ed4=(Button) findViewById(R.id.ed4);

        ed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://www.youtube.com/channel/UCPPIsrNlEkaFQBk-4uNkOaw";
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);
            }
        });

        ed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://www.youtube.com/channel/UCK2NrhorDtFdNNtNs-66JZQ";
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);
            }
        });

        ed3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://www.youtube.com/channel/UCPiuJvuyMVFSwcIdBCTpTyQ";
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);
            }
        });

        ed4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://www.youtube.com/channel/UCvScO0Q9oYu0A2bLjWwAJXA";
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);
            }
        });















    }
}
