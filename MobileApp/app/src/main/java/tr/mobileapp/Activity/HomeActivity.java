package tr.mobileapp.Activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import tr.mobileapp.Entity.Account;
import tr.mobileapp.Entity.Tour;
import tr.mobileapp.Helper.SharedPreferenceHelper;
import tr.mobileapp.R;
import tr.mobileapp.Validate.GenerateTripValidate;
import tr.mobileapp.VolleySingleton;

public class HomeActivity extends AppCompatActivity {

    private Button btnPlanning, btnGenerateTrip;
    private EditText idLocation, idBudget, idStartDate, idEndDate;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog alertDialog;
    DatePickerDialog.OnDateSetListener setStartDateListener, setEndDateListener;
    RequestQueue requestQueue;
    private GenerateTripValidate generateTripValidate = new GenerateTripValidate();
    SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bindingView();
        bindingAction();

        requestQueue = Volley.newRequestQueue(this);
    }

    public void bindingView() {
        btnPlanning = findViewById(R.id.idPlanning);
    }

    public void bindingViewPopup(View popupView) {
        btnGenerateTrip = popupView.findViewById(R.id.idGenerateTrip);
        idLocation = popupView.findViewById(R.id.idEdtLocation);
        idBudget = popupView.findViewById(R.id.idEdtBudget);
        idStartDate = popupView.findViewById(R.id.idEdtStartDate);
        idEndDate = popupView.findViewById(R.id.idEdtEndDate);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.idItemSavedTrip:
                moveToSavedStrip("http://10.0.2.2:8080/trip/getByAccount/1", this);
                return true;
            case R.id.idItemFavourite:
                return true;
            case R.id.idItemSetting:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void moveToSavedStrip(String url, Context context) {
        JSONObject jsonObjectRequest = new JSONObject();

        // add walletId account
        String accountString = sharedPreferenceHelper.getDataFromPref(context, "MyPref", "account");
        Gson gson = new Gson();
        String accountId = gson.fromJson(accountString, Account.class).getId();

        Log.d("TEST", "-------------");
        Log.d("TEST", jsonObjectRequest.toString());
        // Make request for JSONObject
        JsonArrayRequest jsonArrayReq = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Intent i = new Intent(context, SavedTripActivity.class);
                        i.putExtra("response", response.toString());

                        startActivity(i);

//                        ArrayList<Tour> tours = new ArrayList<>();
//                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyy-MM-dd");
//                        for (int i = 0; i < response.length(); i++) {
//                            try {
//                                JSONObject tourObj = response.getJSONObject(i);
//                                int id = tourObj.getInt("id");
//                                Date startDate = new java.sql.Date(dateFormat.parse(tourObj.getString("startDate")).getTime());
//                                Date endDate = new java.sql.Date(dateFormat.parse(tourObj.getString("endDate")).getTime());
//                                int numberOfDays = tourObj.getInt("numberOfDays");
//
//                                Tour tour = new Tour(id, startDate, endDate, numberOfDays);
//                                tours.add(tour);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            } catch (ParseException e) {
//                                e.printStackTrace();
//                            }
//                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
            }
        }) {

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

        };
//        Volley.newRequestQueue(this).add(jsonObjReq);
        VolleySingleton.getmInstance(getApplicationContext()).addToRequestQueue(jsonArrayReq);
//        VolleySingleton.getmInstance(getApplicationContext());

    }

    private void bindingAction() {
        btnPlanning.setOnClickListener(this::onIdPlanning);
    }

    private void onIdPlanning(View view) {
        generateTripPopup();
    }

    private void onGenerateTrip(View view) {
        Sync("http://10.0.2.2:8080/trip/generate", this);
    }

    public void Sync(String url, Context context) {
        JSONObject jsonObjectRequest = new JSONObject();
        try {
            // add walletId account
            String accountString = sharedPreferenceHelper.getDataFromPref(context, "MyPref", "account");
            Gson gson = new Gson();
            JSONObject account = gson.fromJson(accountString, Account.class).toJson();
//            Account accountE = new Account();
//            JSONObject account = accountE.toJson();

            // add input popup
            String location = idLocation.getText().toString();
            String budget = idBudget.getText().toString();
            String startDate = idStartDate.getText().toString();
            String endDate = idEndDate.getText().toString();

            boolean isVerified = generateTripValidate.generateTripValidate(location, budget, startDate, endDate);
            if (isVerified) {
                jsonObjectRequest.put("walletId", account);
                jsonObjectRequest.put("destination", location);
                jsonObjectRequest.put("budget", budget);
                jsonObjectRequest.put("startDate", startDate);
                jsonObjectRequest.put("endDate", endDate);
            } else {
                Toast.makeText(context, "Validate failed", Toast.LENGTH_SHORT).show();
                return;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("TEST", "-------------");
        Log.d("TEST", jsonObjectRequest.toString());
        // Make request for JSONObject
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, url, jsonObjectRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Intent i = new Intent(context, TripPlanActivity.class);
                        i.putExtra("response", response.toString());

                        startActivity(i);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
            }
        }) {

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

        };
//        Volley.newRequestQueue(this).add(jsonObjReq);
        VolleySingleton.getmInstance(getApplicationContext()).addToRequestQueue(jsonObjReq);
//        VolleySingleton.getmInstance(getApplicationContext());

    }

    private Map<String, String> setHeaderData() {
        Map<String, String> headers = new HashMap<>();
        headers.put("header", "Sample header");
        return headers;
    }

    /* Popup Dialog */
    public void generateTripPopup() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View popupView = getLayoutInflater().inflate(R.layout.activity_popup_generate_trip, null);

        // Input itinerary plan
        bindingViewPopup(popupView);
        datePicker();

        dialogBuilder.setView(popupView);
        alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

        // Button event
        btnGenerateTrip.setOnClickListener(this::onGenerateTrip);
    }

    public void datePicker() {
        //Input Date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        idStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v, setStartDateListener, year, month, day);
                Log.d("TAG", "onClick: START");
            }
        });
        idEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v, setEndDateListener, year, month, day);
                Log.d("TAG", "onClick: END");
            }
        });

        setStartDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = year + "-" + month + "-" + dayOfMonth;
                idStartDate.setText(date);
            }
        };
        setEndDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = year + "-" + month + "-" + dayOfMonth;
                idEndDate.setText(date);
            }
        };
    }

    public void showDatePickerDialog(View v, DatePickerDialog.OnDateSetListener listener,
                                     int year, int month, int day) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(HomeActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                listener, year, month, day);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }
}

