package com.example.corona_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class stateActivity extends AppCompatActivity {

    private TextView tv_total,tv_deceased,tv_recovered,tv_tested,tv_first,tv_second,tv_stateName;
    private String valueFromSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

        tv_total=findViewById(R.id.tv_total);
        tv_deceased=findViewById(R.id.tv_deceased);
        tv_recovered=findViewById(R.id.tv_recovered);
        tv_tested=findViewById(R.id.tv_tested);
        tv_first=findViewById(R.id.tv_first);
        tv_second=findViewById(R.id.tv_second);

        tv_stateName=findViewById(R.id.tv_stateName);



        valueFromSpinner=(String)getIntent().getSerializableExtra("state");

        tv_stateName.setText(valueFromSpinner);
//
//        Toast.makeText(this, valueFromSpinner, Toast.LENGTH_SHORT).show();

        fetchdata();
    }
    private void fetchdata(){
        String url = "https://data.covid19india.org/v4/min/data.min.json";

        StringRequest request=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response.toString());

                            tv_total.setText(jsonObject.getJSONObject(valueFromSpinner).getJSONObject("total").getString("confirmed").toString());
                            tv_deceased.setText(jsonObject.getJSONObject(valueFromSpinner).getJSONObject("total").getString("deceased").toString());
                            tv_recovered.setText(jsonObject.getJSONObject(valueFromSpinner).getJSONObject("total").getString("recovered").toString());
                            tv_tested.setText(jsonObject.getJSONObject(valueFromSpinner).getJSONObject("total").getString("tested").toString());
                            tv_first.setText(jsonObject.getJSONObject(valueFromSpinner).getJSONObject("total").getString("vaccinated1").toString());
                            tv_second.setText(jsonObject.getJSONObject(valueFromSpinner).getJSONObject("total").getString("vaccinated2").toString());

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(stateActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}