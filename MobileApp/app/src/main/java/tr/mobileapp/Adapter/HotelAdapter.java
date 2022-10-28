package tr.mobileapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import tr.mobileapp.Entity.RestingPlace;
import tr.mobileapp.R;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<RestingPlace> restingPlaces;

    public HotelAdapter(Context context, ArrayList<RestingPlace> restingPlaces)
    {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.restingPlaces = restingPlaces;
    }

    @NonNull
    @Override
    public HotelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View hotelViewItem;
        hotelViewItem = inflater.inflate(R.layout.hotel_item_layout, parent, false);
        return new HotelAdapter.ViewHolder(hotelViewItem, context);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelAdapter.ViewHolder holder, int position) {
        RestingPlace restingPlace = restingPlaces.get(position);
        try
        {
            holder.setData(restingPlace);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return restingPlaces.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtHotelName, txtHotelLocation, txtHotelPrice, txtHotelTime, txtHotelDescription;
        private Button btnBookHotel;
        private Context context;

        private void bindingView(View view)
        {
            txtHotelName = view.findViewById(R.id.txtHotelName);
            txtHotelLocation = view.findViewById(R.id.txtHotelLocation);
            txtHotelPrice = view.findViewById(R.id.txtHotelPrice);
            txtHotelTime = view.findViewById(R.id.txtHotelTime);
            txtHotelDescription = view.findViewById(R.id.txtHotelDescription);
            btnBookHotel = view.findViewById(R.id.btnBookHotel);
        }

        private void bindingAction()
        {

        }

        public void setData(RestingPlace restingPlace) throws ParseException
        {
            txtHotelName.setText(restingPlace.getName());
            txtHotelLocation.setText(restingPlace.getLocation());
            txtHotelPrice.setText(new StringBuilder().append(restingPlace.getPrice()).append(" VND/night").toString());
            txtHotelTime.setText(new StringBuilder().append("Open from: ")
                    .append(convertDoubleToHour(restingPlace.getOpenTime())).append(" - ")
                    .append(convertDoubleToHour(restingPlace.getCloseTime())).toString());
            txtHotelDescription.setText(restingPlace.getDescription());
        }

        public String convertDoubleToHour(double doubleHour)  throws ParseException {
            int hour = (int)doubleHour;

            SimpleDateFormat sdf = new SimpleDateFormat("ss");

            Date dt = sdf.parse(String.valueOf(hour));
            sdf = new SimpleDateFormat("HH:mm");
            return sdf.format(dt);
        }

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            bindingView(itemView);
            bindingAction();
        }
    }
}
