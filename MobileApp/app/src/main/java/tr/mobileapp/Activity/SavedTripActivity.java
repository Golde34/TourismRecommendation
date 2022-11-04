package tr.mobileapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import tr.mobileapp.Adapter.TourAdapter;
import tr.mobileapp.Entity.DayOfTrip;
import tr.mobileapp.Entity.MyPOI;
import tr.mobileapp.Entity.POIOfDay;
import tr.mobileapp.Entity.Tour;
import tr.mobileapp.R;
import tr.mobileapp.Ultility.RecyclerItemClickListener;

public class SavedTripActivity extends AppCompatActivity {

    private RecyclerView rcvSavedTrip;
    private ArrayList<Tour> tourArrayList;
    private Context context = this;
    JSONArray jsonArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_trip);

        bindingView();

        Intent intent = getIntent();

        String response = intent.getStringExtra("response");

        tourArrayList = new ArrayList<Tour>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyy-MM-dd");
        try {
            jsonArr = new JSONArray(response);
            for (int i = 0; i < jsonArr.length(); i++) {
                JSONObject tourObj = jsonArr.getJSONObject(i);

                JSONArray daysOfTrip = tourObj.getJSONArray("listDays");

                int id = tourObj.getInt("id");
                Date startDate = new java.sql.Date(dateFormat.parse(tourObj.getString("startDate")).getTime());
                Date endDate = new java.sql.Date(dateFormat.parse(tourObj.getString("endDate")).getTime());
                int numberOfDays = tourObj.getInt("numberOfDays");

                Tour tour = new Tour(id, startDate, endDate, numberOfDays);
                tourArrayList.add(tour);
            }
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }

        TourAdapter adapter = new TourAdapter(this, tourArrayList);
        rcvSavedTrip.setAdapter(adapter);
        int numberOfColumns = 2;
        rcvSavedTrip.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        rcvSavedTrip.addOnItemTouchListener(new RecyclerItemClickListener(this, rcvSavedTrip, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                try {
                    JSONObject tourObj = jsonArr.getJSONObject(position);

                    Intent i = new Intent(context, TripPlanActivity.class);
                    i.putExtra("response", tourObj.toString());

                    startActivity(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }

    private void bindingView() {
        rcvSavedTrip = findViewById(R.id.idRcvSavedTrip);
    }

}