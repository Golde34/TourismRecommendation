package tr.mobileapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import tr.mobileapp.Activity.HomeActivity;
import tr.mobileapp.Entity.Account;
import tr.mobileapp.Entity.Tour;
import tr.mobileapp.R;
import tr.mobileapp.VolleySingleton;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Tour> tourArrayList;

    public TourAdapter(Context context, ArrayList<Tour> tourArrayList) {
        this.context = context;
        this.tourArrayList = tourArrayList;
    }

    @NonNull
    @Override
    public TourAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tour_item, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull TourAdapter.ViewHolder holder, int position) {
            Tour tour = tourArrayList.get(position);
            if (tour != null) {
                holder.setTourData(tour);
            } else {
                return;
            }
    }

    @Override
    public int getItemCount() {
        return tourArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Context context;
        TextView tvTourId;
        TextView tvStartDate;
        TextView tvEndDate;
        ImageView ivTour;

        ArrayList<Integer> images = new ArrayList<>();


        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            images.add(R.drawable.random_tour_item);
            images.add(R.drawable.random_tour_item_2);
            images.add(R.drawable.random_tour_item_3);
            images.add(R.drawable.tour_random_item_4);

            this.context = context;
            bindingView(itemView);
        }

        private void bindingView(View itemView) {
            tvTourId = itemView.findViewById(R.id.idTvTourId);
            tvStartDate = itemView.findViewById(R.id.idTvStartDate);
            tvEndDate = itemView.findViewById(R.id.idTvEndDate);

            ivTour = itemView.findViewById(R.id.idIVPlace);
        }

        public void setTourData(Tour tour) {
            tvTourId.setText(String.valueOf(tour.getId()));
            tvStartDate.setText("Start Date: "+tour.getStartDate().toString());
            tvEndDate.setText("End Date: "+tour.getEndDate().toString());

            int min = 0;
            int max = images.size()-1;
            int random = new Random().nextInt((max - min) + 1) + min;

            ivTour.setImageResource(images.get(random));
        }
    }
}
