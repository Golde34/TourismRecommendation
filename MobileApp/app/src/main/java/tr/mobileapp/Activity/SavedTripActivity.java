package tr.mobileapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

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

public class SavedTripActivity extends AppCompatActivity {

    private RecyclerView rcvSavedTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_trip);

        bindingView();

        Intent intent = getIntent();

        String response = intent.getStringExtra("response");

        JSONArray jsonArr;

        ArrayList<Tour> tourArrayList = new ArrayList<Tour>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyy-MM-dd");
        try {
            jsonArr = new JSONArray(response);
            for (int i = 0; i < jsonArr.length(); i++) {
                JSONObject tourObj = jsonArr.getJSONObject(i);

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
        rcvSavedTrip.setLayoutManager(new LinearLayoutManager(this));
    }

    private void bindingView() {
        rcvSavedTrip = findViewById(R.id.idRcvSavedTrip);
    }
}