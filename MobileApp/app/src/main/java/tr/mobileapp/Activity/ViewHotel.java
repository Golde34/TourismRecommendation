package tr.mobileapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import tr.mobileapp.Adapter.HotelAdapter;
import tr.mobileapp.Entity.RestingPlace;
import tr.mobileapp.R;

public class ViewHotel extends AppCompatActivity {

    private RecyclerView rcvHotelList;

    private void bindingView()
    {
        rcvHotelList = findViewById(R.id.rcvHotelList);
    }

    private void bindingAction()
    {
        Intent intent = getIntent();
        String response = intent.getStringExtra("response");
        ArrayList<RestingPlace> restingPlaceArrayList = new ArrayList<>();

        try
        {
            JSONArray restingPlaces = new JSONArray(response);
            for (int i = 0; i < restingPlaces.length(); ++i)
            {
                RestingPlace place = new RestingPlace();
                place.setId(restingPlaces.getJSONObject(i).getInt("restId"));
                place.setOpenTime(restingPlaces.getJSONObject(i).getDouble("openTime"));
                place.setCloseTime(restingPlaces.getJSONObject(i).getDouble("closeTime"));
                place.setDescription(restingPlaces.getJSONObject(i).getString("description"));
                place.setName(restingPlaces.getJSONObject(i).getString("name"));
                place.setLocation(restingPlaces.getJSONObject(i).getString("location"));
                place.setPrice(restingPlaces.getJSONObject(i).getDouble("price"));
                place.setTotalRating(restingPlaces.getJSONObject(i).getInt("totalRating"));
                place.setCityName(restingPlaces.getJSONObject(i).getJSONObject("city").getString("cityName"));

                restingPlaceArrayList.add(place);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        HotelAdapter adapter = new HotelAdapter(this, restingPlaceArrayList);
        rcvHotelList.setAdapter(adapter);
        rcvHotelList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hotel);

        bindingView();
        bindingAction();
    }
}