package tr.mobileapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tr.mobileapp.Entity.TripDetailRVModal;
import tr.mobileapp.R;

public class TripDetailRVAdapter extends RecyclerView.Adapter<TripDetailRVAdapter.ViewHolder>{

    private Context context;
    private ArrayList<TripDetailRVModal> tripDetailRVModalArrayList;
    private LayoutInflater inflater;

    public TripDetailRVAdapter(Context context, ArrayList<TripDetailRVModal> tripDetailRVModalArrayList) {
        this.context = context;
        this.tripDetailRVModalArrayList = tripDetailRVModalArrayList;
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
        TripDetailRVModal tripDetail = tripDetailRVModalArrayList.get(position);
        holder.setData(tripDetail);
    }

    @Override
    public int getItemCount() {
        return tripDetailRVModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView openTimeTV, closeTimeTV, nameTV, locationTV, descriptionTV, ratingTV;
        private ImageView placeIV;
        private RatingBar ratingRB;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setData(TripDetailRVModal tripDetail){
            openTimeTV.setText(String.valueOf(tripDetail.getOpenTime()));
            closeTimeTV.setText(String.valueOf(tripDetail.getCloseTime()));
            nameTV.setText(tripDetail.getName());
            locationTV.setText(tripDetail.getLocation());
            descriptionTV.setText(tripDetail.getDescription());
            ratingTV.setText(String.valueOf(tripDetail.getTotalRating()));
            ratingRB.setRating((float) tripDetail.getTotalRating());
        }
    }
}
