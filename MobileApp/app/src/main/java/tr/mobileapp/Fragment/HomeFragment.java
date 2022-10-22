package tr.mobileapp.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import tr.mobileapp.Activity.HomeActivity;
import tr.mobileapp.Activity.TripPlanActivity;
import tr.mobileapp.Entity.Account;
import tr.mobileapp.Helper.SharedPreferenceHelper;
import tr.mobileapp.R;
import tr.mobileapp.Validate.GenerateTripValidate;
import tr.mobileapp.VolleySingleton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private Context context;
    private Button btnPlanning, btnGenerateTrip;
    private EditText idLocation, idBudget, idStartDate, idEndDate;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog alertDialog;
    DatePickerDialog.OnDateSetListener setStartDateListener, setEndDateListener;
    RequestQueue requestQueue;
    private GenerateTripValidate generateTripValidate = new GenerateTripValidate();
    SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper();

    public HomeFragment(Context context) {
        this.context = context;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        bindingView(v);
        bindingAction();

        requestQueue = Volley.newRequestQueue(context);

        return v;
    }

    public void bindingView(View v) {
        btnPlanning = v.findViewById(R.id.idPlanning);
    }

    public void bindingViewPopup(View popupView) {
        btnGenerateTrip = popupView.findViewById(R.id.idGenerateTrip);
        idLocation = popupView.findViewById(R.id.idEdtLocation);
        idBudget = popupView.findViewById(R.id.idEdtBudget);
        idStartDate = popupView.findViewById(R.id.idEdtStartDate);
        idEndDate = popupView.findViewById(R.id.idEdtEndDate);
    }

    private void bindingAction() {
        btnPlanning.setOnClickListener(this::onIdPlanning);
    }

    private void onIdPlanning(View view) {
        generateTripPopup();
    }

    private void onGenerateTrip(View view) {
        Sync("http://10.0.2.2:8080/trip/generate", context);
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
        VolleySingleton.getmInstance(context.getApplicationContext()).addToRequestQueue(jsonObjReq);
//        VolleySingleton.getmInstance(getApplicationContext());

    }

    private Map<String, String> setHeaderData() {
        Map<String, String> headers = new HashMap<>();
        headers.put("header", "Sample header");
        return headers;
    }

    /* Popup Dialog */
    public void generateTripPopup() {
        dialogBuilder = new AlertDialog.Builder(context);
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
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                listener, year, month, day);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }
}