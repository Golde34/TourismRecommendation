package tr.mobileapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import tr.mobileapp.Entity.ReviewPOI;
import tr.mobileapp.R;

public class Recyclerview_adapter extends RecyclerView.Adapter<Recyclerview_adapter.ViewHolder>  {

    Context context;
    List<ReviewPOI> list;

    public Recyclerview_adapter(Context context, List<ReviewPOI> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review_two,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ReviewPOI model_eating = list.get(position);

        holder.tv_rating.setText(String.valueOf(model_eating.getRate()));

        holder.tv_title.setText(model_eating.getTitle());

        holder.tv_des.setText(model_eating.getComment());

        holder.tv_username_time.setText(model_eating.getAccountName() + ", " +model_eating.getTimeCreate());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_rating;
        TextView  tv_title;
        TextView  tv_des;
        TextView  tv_username_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_rating = itemView.findViewById(R.id.tv_rating);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_des = itemView.findViewById(R.id.tv_des);
            tv_username_time = itemView.findViewById(R.id.tv_username_time);
        }
    }
}
