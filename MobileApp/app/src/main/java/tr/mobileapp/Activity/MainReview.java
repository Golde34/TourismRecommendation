package tr.mobileapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import tr.mobileapp.R;
import tr.mobileapp.VolleySingleton;

public class MainReview extends AppCompatActivity {

    private Button btnReview;
    int onResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(tr.mobileapp.R.layout.activity_main_review);
        Intent intent = getIntent();
        onResponse = intent.getIntExtra("poiID",0);
        btnReview = findViewById(R.id.btGoBackReview);
        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TakeReviewPOI();
            }
        });
    }

    private void TakeReviewPOI() {
        Sync("http://10.0.2.2:8080/ReviewPOI/getRating1/", this);
    }

    private void Sync(String url, Context context)  {
        JsonArrayRequest jsonArrReq = new JsonArrayRequest(Request.Method.POST,
                url+ String.valueOf(onResponse),null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Intent i = new Intent(context, MainReviewTwo.class);
                        i.putExtra("response", response.toString());
                        i.putExtra("poiID",onResponse );
                        startActivity(i);
                        finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
            }
        });
        VolleySingleton.getmInstance(getApplicationContext()).addToRequestQueue(jsonArrReq);
    }
}