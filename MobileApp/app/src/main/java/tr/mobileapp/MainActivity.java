package tr.mobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.viewpager.widget.ViewPager;

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
import com.google.android.material.tabs.TabLayout;
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
import tr.mobileapp.Adapter.FragmentSectionAdapter;
import tr.mobileapp.Database.AppDatabase;
import tr.mobileapp.Entity.Account;
import tr.mobileapp.Entity.Role;
import tr.mobileapp.Fragment.FavouriteFragment;
import tr.mobileapp.Fragment.HomeFragment;
import tr.mobileapp.Fragment.SavedTripFragment;
import tr.mobileapp.Fragment.SettingFragment;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentSectionAdapter adapter;
    private static final String DB_NAME = "tourismRecommendation.db";
  
    private void bindingView(){
        tabLayout = findViewById(R.id.idTlNavigation);
        viewPager = findViewById(R.id.idVpContent);
        adapter = new FragmentSectionAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    private void bindingAction(){
        tabLayout.setupWithViewPager(viewPager);

        adapter.addFragment(new HomeFragment(), "HOME");
        adapter.addFragment(new SavedTripFragment(this), "HISTORY");
        adapter.addFragment(new FavouriteFragment(), "FAVOUR");

        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindingView();
        bindingAction();

        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "Sample.db")
                .addMigrations(MIGRATION_1_2)
                .build();
        Log.d("DATABASE", "Successful create db: " + database.isOpen());
    }

//    public void Sync(String url, Context context) {
//        JSONObject jsonObjectRequest = new JSONObject();
//
//        try {
//            Account accountE = new Account();
//            JSONObject account = accountE.toJson();
//
//            jsonObjectRequest.put("walletId", account);
//            jsonObjectRequest.put("destination", null);
//            jsonObjectRequest.put("budget", null);
//            jsonObjectRequest.put("startDate", "2022-08-23");
//            jsonObjectRequest.put("endDate", "2022-08-28");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        Log.d("TEST", "-------------");
//        Log.d("TEST", jsonObjectRequest.toString());
//        // Make request for JSONObject
//        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
//                Request.Method.POST, url, jsonObjectRequest,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Intent i = new Intent(context, TripPlanActivity.class);
//                        i.putExtra("response", response.toString());
//
//                        startActivity(i);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d("TAG", "Error: " + error.getMessage());
//            }
//        }) {
//
//            /**
//             * Passing some request headers
//             */
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json; charset=utf-8");
//                return headers;
//            }
//
//        };
////        Volley.newRequestQueue(this).add(jsonObjReq);
//        VolleySingleton.getmInstance(getApplicationContext()).addToRequestQueue(jsonObjReq);
////        VolleySingleton.getmInstance(getApplicationContext());
//
//    }
//
//    private Map<String, String> setHeaderData() {
//        Map<String, String> headers = new HashMap<>();
//        headers.put("header", "Sample header");
//        return headers;
//    }
//
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
       public void migrate(SupportSQLiteDatabase database) {
           // Empty implementation, because the schema isn't changing.
        }
   };
}