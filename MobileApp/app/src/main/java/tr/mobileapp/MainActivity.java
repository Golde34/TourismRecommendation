package tr.mobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import tr.mobileapp.Activity.TripPlanActivity;
import tr.mobileapp.Database.AppDatabase;
import tr.mobileapp.Entity.Account;
import tr.mobileapp.Entity.Role;

public class MainActivity extends AppCompatActivity {

    private Button buttonClick;
    RequestQueue requestQueue;
    private static final String DB_NAME = "tourismRecommendation.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonClick = findViewById(R.id.buttonTest);
        requestQueue = Volley.newRequestQueue(this);
        buttonClick.setOnClickListener((v) -> {
            Sync("http://10.0.2.2:8080/trip/generate", this);
        });
        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "Sample.db")
                .addMigrations(MIGRATION_1_2)
                .build();
        Log.d("DATABASE", "Successful create db: " + database.isOpen());
    }

    public void Sync(String url, Context context) {
        JSONObject jsonObjectRequest = new JSONObject();
        try {
            JSONObject account = new JSONObject();
            account.put("id", "1");
            account.put("address", "gia loc");
            account.put("DOB", "2001-04-03");
            account.put("email", "nguyendongducviet2001@gmail.com");
            account.put("fullname", "Nguyen Viet");
            account.put("gender", "male");
            account.put("image", null);
            account.put("phoneNumber", "0343978156");
            account.put("level", 0);
            account.put("levelPoint", 0);
            account.put("status", 1);
            account.put("username", "golde");
            JSONObject role = new JSONObject();
            role.put("roleId", 1);
            role.put("roleName", "user");
            account.put("role", role);

            jsonObjectRequest.put("walletId", account);
            jsonObjectRequest.put("destination", null);
            jsonObjectRequest.put("budget", null);
            jsonObjectRequest.put("startDate", "2022-08-23");
            jsonObjectRequest.put("endDate", "2022-08-28");
        } catch (JSONException e) {
            e.printStackTrace();
        }

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

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Empty implementation, because the schema isn't changing.
        }
    };


}