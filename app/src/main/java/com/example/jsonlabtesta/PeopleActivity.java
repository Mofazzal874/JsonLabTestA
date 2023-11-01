package com.example.jsonlabtesta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PeopleActivity extends AppCompatActivity {


    ListView lst  ;
    ArrayList<String> names = new ArrayList<String>() ;
    ArrayList<String> ages = new ArrayList<String>() ;
    ArrayList<String> addresses = new ArrayList<String>() ;
    ArrayList<String> isMarrieds = new ArrayList<String>() ;


    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        lst = findViewById(R.id.lstView) ;

        requestQueue = Volley.newRequestQueue(this) ;

        Intent i = getIntent() ;
        String companyName = i.getStringExtra("company").toString();


        String url = "https://api.myjson.online/v1/records/53b4282e-3ea7-4c05-b9b2-b9825c0c91f0" ;
        JsonObjectRequest  jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray =response.getJSONArray("data") ;
                    for(int i = 0 ;i < jsonArray.length() ; i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i) ;
                        if(jsonObject.getString("company").equals(companyName)){
                           JSONArray jsonArray1 = jsonObject.getJSONArray("people") ;
                           for(int j = 0 ; j < jsonArray1.length();j++){
                               JSONObject parPeople = jsonArray1.getJSONObject(j) ;
                               String name = parPeople.getString("name") ;
                               names.add(name) ;

                               JSONObject add = parPeople.getJSONObject("address") ;
                               String a= add.getString("city");
                               String b = add.getString("street");
                               String c = add.getString("zipCode");
                               addresses.add("City: " + a+ "\nStreet: " + b+ "\nZipcode: "+c) ;

                               ArrayAdapter arrayAdapter = new ArrayAdapter(PeopleActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, names) ;
                               lst.setAdapter(arrayAdapter);
                                lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent1 = new Intent(PeopleActivity.this, ShowActivity.class);

                                        intent1.putExtra("address", addresses.get(position));
                                        startActivity(intent1);
                                    }
                                });

                           }
                           break;
                        }

                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) ;
        requestQueue.add(jsonObjectRequest) ;
    }
}