package tr.mobileapp.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import tr.mobileapp.Adapter.TourAdapter;
import tr.mobileapp.Entity.Tour;
import tr.mobileapp.R;
import tr.mobileapp.VolleySingleton;

public class SavedTripFragment extends Fragment {

    private Context context;
    private RecyclerView rcvTour;
    private ArrayList<Tour> tourArrayList;
    private TourAdapter adapter;

    public SavedTripFragment(Context context) {
        this.context = context;
    }

    private void bindingView(View v){
        rcvTour = v.findViewById(R.id.idRcvTour);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tourArrayList = Sync("http://10.0.2.2:8080/trip/getByAccount/1", context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_saved_trip, container, false);
        bindingView(v);

        adapter = new TourAdapter(context, tourArrayList);
        rcvTour.setLayoutManager(new LinearLayoutManager(context));
        rcvTour.setAdapter(adapter);

        // Inflate the layout for this fragment
        return v;
    }

    public ArrayList<Tour> Sync(String url, Context context) {
        JSONObject jsonObjectRequest = new JSONObject();

        ArrayList<Tour> tourList = new ArrayList<>();

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
        Log.d("TEST", "-------------");
        Log.d("TEST", jsonObjectRequest.toString());
        // Make request for JSONObject
        JsonArrayRequest jsonArrayReq = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<Tour> tours = new ArrayList<>();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyy-MM-dd");
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject tourObj = response.getJSONObject(i);
                                int id = tourObj.getInt("id");
                                Date startDate = new java.sql.Date(dateFormat.parse(tourObj.getString("startDate")).getTime());
                                Date endDate = new java.sql.Date(dateFormat.parse(tourObj.getString("endDate")).getTime());
                                int numberOfDays = tourObj.getInt("numberOfDays");

                                Tour tour = new Tour(id, startDate, endDate, numberOfDays);
                                tours.add(tour);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }

                        tourList.clear();
                        tourList.addAll(tours);

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
        VolleySingleton.getmInstance(context).addToRequestQueue(jsonArrayReq);
//        VolleySingleton.getmInstance(getApplicationContext());


        return tourList;
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