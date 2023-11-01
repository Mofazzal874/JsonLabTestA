package com.example.jsonlabtesta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private Button btn ;
    private EditText txt ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.searchbtn)  ;
        txt  = findViewById(R.id.enterCompany) ;
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String input = txt.getText().toString() ;
        Intent i = new Intent(MainActivity.this ,PeopleActivity.class) ;
        i.putExtra("company" , input) ;
        startActivity(i);

    }
}