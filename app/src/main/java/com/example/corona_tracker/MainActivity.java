package com.example.corona_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity {

    private TextView tvCases, tvRecovered, tvCritical, tvActive, tvTodayCases, tvTotalDeaths, tvTodayDeaths, tvAffectedCountries;

    private Button btn_start;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        tvCases = findViewById(R.id.tvCases);
//        tvRecovered = findViewById(R.id.tvRecovered);
//        tvCritical = findViewById(R.id.tvCritical);
//        tvActive = findViewById(R.id.tvActive);
//        tvTodayCases = findViewById(R.id.tvTodayCases);
//        tvTotalDeaths = findViewById(R.id.tvTotalDeaths);
//        tvTodayDeaths = findViewById(R.id.tvTodayDeaths);
//        tvAffectedCountries = findViewById(R.id.tvAffectedCountries);
//
//        fetchdata();

        btn_start=findViewById(R.id.btn_getStarted);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,HomeActivity.class));
                finish();
            }
        });



    }

//    private void fetchdata(){
//        String url = "https://disease.sh/v3/covid-19/all";
//
//        StringRequest request
//                = new StringRequest(
//                Request.Method.GET,
//                url,
//                new Response.Listener<String>() {
//
//                    @Override
//                    public void onResponse(String response)
//                    {
//                        // Handle the JSON object and handle it inside try and catch
//
//                        try {
//                            // Creating object of JSONObject
//                            JSONObject jsonObject
//                                    = new JSONObject(
//                                    response.toString());
//
//                            // Set the data in text view
//                            // which are available in JSON format
//                            // Note that the parameter
//                            // inside the getString() must match
//                            // with the name given in JSON format
//                            tvCases.setText(
//                                    jsonObject.getString("cases"));
//                            tvRecovered.setText(
//                                    jsonObject.getString("recovered"));
//                            tvCritical.setText(
//                                    jsonObject.getString("critical"));
//                            tvActive.setText(
//                                    jsonObject.getString("active"));
//                            tvTodayCases.setText(
//                                    jsonObject.getString("todayCases"));
//                            tvTotalDeaths.setText(
//                                    jsonObject.getString("deaths"));
//                            tvTodayDeaths.setText(
//                                    jsonObject.getString("todayDeaths"));
//                            tvAffectedCountries.setText(
//                                    jsonObject.getString("affectedCountries"));
//                        }
//                        catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(
//                            VolleyError error)
//                    {
//                        Toast.makeText(MainActivity.this,
//                                error.getMessage(),
//                                Toast.LENGTH_SHORT)
//                                .show();
//                    }
//                });
//
//        RequestQueue requestQueue
//                = Volley.newRequestQueue(this);
//        requestQueue.add(request);
//    }
}