package com.example.corona_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView tv_wwCases,tv_todayww;
    private Spinner spinnerSelect;
    private ImageButton ibtn_selectState;
    private String valueFromSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tv_wwCases=findViewById(R.id.tv_wwCases);
        tv_todayww=findViewById(R.id.tv_todayww);
        spinnerSelect=findViewById(R.id.spinnerSelect);
        ibtn_selectState=findViewById(R.id.ibtn_selectState);



        String[] statesArray=getResources().getStringArray(R.array.states);
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,statesArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSelect.setAdapter(adapter);

        spinnerSelect.setOnItemSelectedListener(this);

        ibtn_selectState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(valueFromSpinner.equals("-select state-")){
                    Toast.makeText(HomeActivity.this, "select the state", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent=new Intent(HomeActivity.this,stateActivity.class);
                    intent.putExtra("state",valueFromSpinner);
                    startActivity(intent);
                }
            }
        });

        fetchdata();
    }

    private void fetchdata(){
        String url = "https://disease.sh/v3/covid-19/all";

        StringRequest request=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response.toString());

                            tv_wwCases.setText(jsonObject.getString("cases"));
                            tv_todayww.setText(jsonObject.getString("todayCases"));
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(HomeActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId()==R.id.spinnerSelect){
            valueFromSpinner=parent.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}