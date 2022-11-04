package tr.mobileapp.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tr.mobileapp.Adapter.TripDateAdapter;
import tr.mobileapp.Adapter.TripDetailRVAdapter;
import tr.mobileapp.Entity.DayOfTrip;
import tr.mobileapp.Entity.MyPOI;
import tr.mobileapp.Entity.POIOfDay;
import tr.mobileapp.R;
import tr.mobileapp.Ultility.RecyclerItemClickListener;
import tr.mobileapp.VolleySingleton;

public class TripPlanActivity extends AppCompatActivity {

    private RecyclerView rcvTripDetail;
    private RecyclerView rcvDayOfTrip;
    private Button btnReviewPOI;

    private Button btnViewHotel;

    ArrayList<POIOfDay> poiOfDays;

    public void bindingView(){
        rcvTripDetail = findViewById(R.id.idRcvTripDetail);
        rcvDayOfTrip = findViewById(R.id.idRcvDayOfTrip);
        btnReviewPOI = findViewById(R.id.idBTNBook);

        btnViewHotel = findViewById(R.id.btnViewHotel);
    }

    private void bindingAction()
    {
        btnViewHotel.setOnClickListener(this::oBtnViewHotelClick);
    }

    private void oBtnViewHotelClick(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            SyncViewHotel("http://10.0.2.2:8080/restingplace/all", this);
        }
    }

    private int randomSeed;
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void SyncViewHotel(String url, Context context) {
        JSONArray jsonObjectRequest = new JSONArray();

        Log.d("TEST", "-------------");
        Log.d("TEST", jsonObjectRequest.toString());
        // Make request for JSONObject
        JsonArrayRequest jsonObjReq = new JsonArrayRequest(
                Request.Method.GET, url, jsonObjectRequest,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Intent i = new Intent(context, ViewHotel.class);
                        i.putExtra("response", response.toString());
                        i.putExtra("randomSeed", randomSeed);

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_plan);

        bindingView();
        bindingAction();

        Intent intent = getIntent();

        String response = intent.getStringExtra("response");

        JSONObject jsonObj;

        ArrayList<DayOfTrip> dayOfTripArrayList = new ArrayList<>();

        try {
            jsonObj = new JSONObject(response);

                JSONArray daysOfTrip = jsonObj.getJSONArray("listDays");
            for (int i = 0; i < daysOfTrip.length(); i++) {

                DayOfTrip day = new DayOfTrip();

                day.setId(daysOfTrip.getJSONObject(i).getInt("id"));
                day.setNumber(daysOfTrip.getJSONObject(i).getInt("number"));
                day.setDate(Date.valueOf(daysOfTrip.getJSONObject(i).getString("date")));

                ArrayList<POIOfDay> poiOfDays = new ArrayList<>();

                // xu ly get List POIOfDay cua ngay
                JSONArray listPOIs = daysOfTrip.getJSONObject(i).getJSONArray("listPOIs");
                for(int j = 0; j < listPOIs.length(); j++){
                    POIOfDay poiOfDay = new POIOfDay();
                    poiOfDay.setId(listPOIs.getJSONObject(j).getInt("id"));
                    poiOfDay.setNumber(listPOIs.getJSONObject(j).getInt("number"));

                    JSONObject poiJSON = listPOIs.getJSONObject(j).getJSONObject("poi");
                    MyPOI poi = new MyPOI();
                    poi.setPOIId(poiJSON.getInt("poiid"));
                    poi.setName(poiJSON.getString("name"));
                    poi.setDescription(poiJSON.getString("description"));
                    poi.setTotalRating(poiJSON.getDouble("totalRating"));
                    poi.setPrice(poiJSON.getDouble("price"));
                    poi.setLocation(poiJSON.getString("location"));
                    poi.setOpenTime(poiJSON.getDouble("openTime"));
                    poi.setCloseTime(poiJSON.getDouble("closeTime"));
                    poi.setDuration(poiJSON.getDouble("duration"));
                    poi.setThumbnail(poiJSON.getString("thumbnail"));
                    poi.setCityName(poiJSON.getJSONObject("city").getString("cityName"));
                    poi.setCountryName(poiJSON.getJSONObject("city").getJSONObject("country").getString("countryName"));

                    poiOfDay.setPoi(poi);

                    poiOfDay.setStartTime(listPOIs.getJSONObject(j).getInt("startTime"));
                    poiOfDay.setEndTime(listPOIs.getJSONObject(j).getInt("endTime"));

                    poiOfDays.add(poiOfDay);
                }

                day.setPoiOfDays(poiOfDays);

                dayOfTripArrayList.add(day);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TripDateAdapter dateAdapter = new TripDateAdapter(this, dayOfTripArrayList);
        rcvDayOfTrip.setAdapter(dateAdapter);

        poiOfDays = new ArrayList<>(dayOfTripArrayList.get(0).getPoiOfDays());

        // Dung xoa cua toi, cai nay de lay random resting place
        randomSeed = poiOfDays.size();

        TripDetailRVAdapter adapter = new TripDetailRVAdapter(this, poiOfDays);
        rcvTripDetail.setAdapter(adapter);

        rcvDayOfTrip.addOnItemTouchListener(new RecyclerItemClickListener(this, rcvDayOfTrip, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                poiOfDays.clear();
                poiOfDays.addAll(dayOfTripArrayList.get(position).getPoiOfDays());
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

    }

    private void Sync(String url, Context context)  {
        JsonArrayRequest jsonArrReq = new JsonArrayRequest(Request.Method.POST,
                url+"1",null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Intent i = new Intent(context, MainReviewTwo.class);
                        i.putExtra("response", response.toString());

                        startActivity(i);
                        finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
            }
        });
        VolleySingleton.getmInstance(getApplicationContext()).addToRequestQueue(jsonArrReq);
    }
    private void TakeReviewPOI() {
        Sync("http://10.0.2.2:8080/ReviewPOI/getRating1/", this);
    }
}