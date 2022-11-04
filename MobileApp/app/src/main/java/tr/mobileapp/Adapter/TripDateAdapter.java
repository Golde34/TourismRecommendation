package tr.mobileapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import tr.mobileapp.Entity.DayOfTrip;
import tr.mobileapp.Entity.POIOfDay;
import tr.mobileapp.R;

public class TripDateAdapter extends RecyclerView.Adapter<TripDateAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Date> dates;
    private LayoutInflater inflater;

    public TripDateAdapter(Context context, ArrayList<DayOfTrip> dayOfTripArrayList) {
        this.context = context;
        this.dates = new ArrayList<>();
        for(int i=0; i<dayOfTripArrayList.size(); i++){
            dates.add(dayOfTripArrayList.get(i).getDate());
        }
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TripDateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View dayOfTripItem = inflater.inflate(R.layout.day_of_trip_item, parent, false);
        return new TripDateAdapter.ViewHolder(dayOfTripItem);
    }

    @Override
    public void onBindViewHolder(@NonNull TripDateAdapter.ViewHolder holder, int position) {
        Date date = dates.get(position);
        holder.setDate(date);
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMonth, tvDate, tvDayOfWeek;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bindingView(itemView);
        }

        private void bindingView(View itemView) {
            tvMonth = itemView.findViewById(R.id.idTVMonth);
            tvDate = itemView.findViewById(R.id.idTVDate);
            tvDayOfWeek = itemView.findViewById(R.id.idTVDayOfWeek);
        }

        public void setDate(Date date) {
            DateFormat dayOfWeekFormat = new SimpleDateFormat("EEE");
            DateFormat monthFormat = new SimpleDateFormat("MMM");
            DateFormat dateFormat = new SimpleDateFormat("dd");

            tvMonth.setText(monthFormat.format(date));
            tvDate.setText(dateFormat.format(date));
            tvDayOfWeek.setText(dayOfWeekFormat.format(date)+".");
        }
    }
}
