package tr.mobileapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tr.mobileapp.R;
import tr.mobileapp.VolleySingleton;

public class ListReview extends AppCompatActivity {

    private LinearLayout lay_one, lay_two;

    private void Sync(String url, Context context)  {
        JsonArrayRequest jsonArrReq = new JsonArrayRequest(Request.Method.POST,
                url+"1",null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                       Intent i = new Intent(context, MainReviewTwo.class);
                        i.putExtra("response", response.toString());
                        startActivity(i);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
            }
        });
        VolleySingleton.getmInstance(getApplicationContext()).addToRequestQueue(jsonArrReq);
    }

    private void TakeReviewPOI() {
        Sync("http://10.0.2.2:8080/ReviewPOI/getRating1/", this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_review);

        lay_one = findViewById(R.id.lay_one);
        lay_two = findViewById(R.id.lay_two);

        lay_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TakeReviewPOI();
            }
        });
    }
}