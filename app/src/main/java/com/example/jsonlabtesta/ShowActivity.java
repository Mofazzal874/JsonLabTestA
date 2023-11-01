package com.example.jsonlabtesta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {

    TextView txt ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        txt = findViewById(R.id.textView) ;

        Intent intent = getIntent() ;
        String addddd = intent.getStringExtra("address");

        txt.setText(addddd);
    }
}