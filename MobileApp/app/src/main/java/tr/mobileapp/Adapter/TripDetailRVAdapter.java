package tr.mobileapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import tr.mobileapp.Activity.HomeActivity;
import tr.mobileapp.Entity.Account;
import tr.mobileapp.Entity.POIOfDay;
import tr.mobileapp.Entity.TripDetailRVModal;
import tr.mobileapp.R;
import tr.mobileapp.Ultility.VolleyCallBack;
import tr.mobileapp.VolleySingleton;

public class TripDetailRVAdapter extends RecyclerView.Adapter<TripDetailRVAdapter.ViewHolder>{

    private Context context;
    private ArrayList<POIOfDay> poiOfDays;
    private LayoutInflater inflater;

    public TripDetailRVAdapter(Context context, ArrayList<POIOfDay> poiOfDays) {
        this.context = context;
        this.poiOfDays = poiOfDays;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TripDetailRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View tripDetailItem = inflater.inflate(R.layout.trip_detail_item, parent, false);
        return new ViewHolder(tripDetailItem);
    }

    @Override
    public void onBindViewHolder(@NonNull TripDetailRVAdapter.ViewHolder holder, int position) {
        POIOfDay poiOfDay = poiOfDays.get(position);
        try {
            holder.setData(poiOfDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return poiOfDays.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView openTimeTV, closeTimeTV, nameTV, descriptionTV, ratingTV;
        private ImageView placeIV;
        private RatingBar ratingRB;
        String searchQuery;
        String imageUri;

        ArrayList<Integer> images = new ArrayList<>();

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bindingView(itemView);

            images.add(R.drawable.destination);
            images.add(R.drawable.flight);
            images.add(R.drawable.map);
            images.add(R.drawable.pamphlet);
            images.add(R.drawable.rickshaw);
            images.add(R.drawable.travel);
        }

        private void bindingView(View view) {
            openTimeTV = view.findViewById(R.id.idTVStartTime);
            closeTimeTV = view.findViewById(R.id.idTVEndTime);
            nameTV = view.findViewById(R.id.idTVNameOfPlace);
//            descriptionTV = view.findViewById(R.id.idTVPlaceDescription);
            ratingTV = view.findViewById(R.id.idTVRating);

            placeIV = view.findViewById(R.id.idIVPlace);
            ratingRB = view.findViewById(R.id.idRBRating);
        }

        public void setData(POIOfDay poiOfDay) throws ParseException {
            searchQuery = poiOfDay.getPoi().getName().trim().replace(" ", "+");

            openTimeTV.setText(convertIntToHour(poiOfDay.getStartTime()));
            closeTimeTV.setText(convertIntToHour(poiOfDay.getEndTime()));
            nameTV.setText(poiOfDay.getPoi().getName().trim());
//            descriptionTV.setText(poiOfDay.getPoi().getDescription().trim());
            ratingTV.setText(Double.toString(poiOfDay.getPoi().getTotalRating()));
            ratingRB.setRating((float) poiOfDay.getPoi().getTotalRating());

            int min = 0;
            int max = images.size()-1;
            int random = new Random().nextInt((max - min) + 1) + min;

            placeIV.setImageResource(images.get(random));
        }


        public String convertIntToHour(int hour)  throws ParseException {
            SimpleDateFormat sdf = new SimpleDateFormat("ss");

            Date dt = sdf.parse(String.valueOf(hour));
            sdf = new SimpleDateFormat("HH:mm");
            return sdf.format(dt);
        }
    }
}
