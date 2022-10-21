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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import tr.mobileapp.Entity.POIOfDay;
import tr.mobileapp.Entity.TripDetailRVModal;
import tr.mobileapp.R;

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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bindingView(itemView);
        }

        private void bindingView(View view) {
            openTimeTV = view.findViewById(R.id.idTVStartTime);
            closeTimeTV = view.findViewById(R.id.idTVEndTime);
            nameTV = view.findViewById(R.id.idTVNameOfPlace);
            descriptionTV = view.findViewById(R.id.idTVPlaceDescription);
            ratingTV = view.findViewById(R.id.idTVRating);

            placeIV = view.findViewById(R.id.idIVPlace);
            ratingRB = view.findViewById(R.id.idRBRating);
        }

        public void setData(POIOfDay poiOfDay) throws ParseException {
            openTimeTV.setText(convertIntToHour(poiOfDay.getStartTime()));
            closeTimeTV.setText(convertIntToHour(poiOfDay.getEndTime()));
            nameTV.setText(poiOfDay.getPoi().getName().trim()+", "+poiOfDay.getPoi().getLocation().trim());
            descriptionTV.setText(poiOfDay.getPoi().getDescription().trim());
            ratingTV.setText(Double.toString(poiOfDay.getPoi().getTotalRating()));
            ratingRB.setRating((float) poiOfDay.getPoi().getTotalRating());
        }

        public String convertIntToHour(int hour)  throws ParseException {
            SimpleDateFormat sdf = new SimpleDateFormat("ss");

            Date dt = sdf.parse(String.valueOf(hour));
            sdf = new SimpleDateFormat("HH:mm");
            return sdf.format(dt);
        }
    }
}
