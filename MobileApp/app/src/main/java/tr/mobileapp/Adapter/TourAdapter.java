package tr.mobileapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tr.mobileapp.Entity.Tour;
import tr.mobileapp.R;

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
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TourAdapter.ViewHolder holder, int position) {
        Tour tour = tourArrayList.get(position);
        holder.setTourData(tour);
    }

    @Override
    public int getItemCount() {
        return tourArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTourId;
        TextView tvStartDate;
        TextView tvEndDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bindingView(itemView);
        }

        private void bindingView(View itemView) {
            tvTourId = itemView.findViewById(R.id.idTvTourId);
            tvStartDate = itemView.findViewById(R.id.idTvStartDate);
            tvEndDate = itemView.findViewById(R.id.idTvEndDate);
        }

        public void setTourData(Tour tour) {
            tvTourId.setText(tour.getId());
            tvStartDate.setText(tour.getStartDate().toString());
            tvEndDate.setText(tour.getEndDate().toString());
        }
    }
}
