package tr.mobileapp.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import tr.mobileapp.Adapter.Recyclerview_adapter;
import tr.mobileapp.Entity.Account;
import tr.mobileapp.Entity.MyPOI;
import tr.mobileapp.Entity.ReviewPOI;
import tr.mobileapp.Helper.SharedPreferenceHelper;
import tr.mobileapp.R;
import tr.mobileapp.Validate.CommentValidation;
import tr.mobileapp.VolleySingleton;

public class MainReviewTwo extends AppCompatActivity {

    RecyclerView recyclerview;
    private Button btnReview,btSummitReview;
    private EditText idTitleReview, idCommentReview;
    private RatingBar ratingBar;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog alertDialog;

    Recyclerview_adapter adapter;
    private ProgressBar oneStar;
    private ProgressBar twoStar;
    private ProgressBar thirtStar;
    private ProgressBar fourStar;
    private ProgressBar fiveStar;
    private TextView countNumber5Star;
    private TextView countNumber4Star;
    private TextView countNumber3Star;
    private TextView countNumber2Star;
    private TextView countNumber1Star;
    private TextView totalVotingStarAverate;
    private TextView totalVotingStar;

    SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper();
    Account acc = new Account();
    int poiID;
    int myRating = 0;
    String onResponse ="";

 public void bindingView(){
     oneStar = findViewById(R.id.tv_Process1Star);
     twoStar = findViewById(R.id.tv_Process2Star);
     thirtStar = findViewById(R.id.tv_Process3Star);
     fourStar = findViewById(R.id.tv_Process4Star);
     fiveStar = findViewById(R.id.tv_Process5Star);
     countNumber5Star = findViewById(R.id.tv_CountNumber5Start);
     countNumber4Star = findViewById(R.id.tv_CountNumber4Start);
     countNumber3Star = findViewById(R.id.tv_CountNumber3Start);
     countNumber2Star = findViewById(R.id.tv_CountNumber2Start);
     countNumber1Star = findViewById(R.id.tv_CountNumber1Start);
     totalVotingStarAverate   = findViewById(R.id.tv_evarageOfStartVoting);
     totalVotingStar   = findViewById(R.id.tv_TotalVotingStart);



 }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_review_two);
        bindingView();
        bindingViewReviewPop();
        bindingAction();
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();

        String response = intent.getStringExtra("response");
        poiID = intent.getIntExtra("poiID",1);
       // onResponse =  intent.getStringExtra("response");
        JSONArray jsonObj;

        ArrayList<ReviewPOI> listReviewPOI = new ArrayList<>();

        try {
            jsonObj = new JSONArray(response);
            for (int i = 0; i < jsonObj.length(); i++) {
                ReviewPOI rvp = new ReviewPOI();
                rvp.setRatingId(jsonObj.getJSONObject(i).getInt("ratingId"));
                rvp.setRate(jsonObj.getJSONObject(i).getInt("rate"));
                rvp.setComment(jsonObj.getJSONObject(i).getString("comment"));
                rvp.setTitle(jsonObj.getJSONObject(i).getString("title"));
                rvp.setTimeCreate(jsonObj.getJSONObject(i).getString("timeCreate"));
                JSONObject Acount = jsonObj.getJSONObject(i).getJSONObject("account");
                rvp.setAccountName(Acount.getString("fullName"));

                listReviewPOI.add(rvp);
//                JSONObject poi = jsonObj.getJSONObject(i).getJSONObject("poi");
//                rvp.setPoiId(poi.getInt("poiid"));
//                poiID = rvp.getPoiId();
            }
                // rvp.setTimeCreate((reviewOfPOI.getJSONObject(i).getString("timeCreate")));
                //ArrayList<Account> AcccountReview = new ArrayList<>();
               // rvp.SetAccountCommentReviewPOI(AcccountReview)

        }catch (
                JSONException e) {
            e.printStackTrace();
        }
        Recyclerview_adapter adapter = new Recyclerview_adapter(this, listReviewPOI);
        recyclerview.setAdapter(adapter);

        Dictionary  viewRatingCount = new Hashtable();
         viewRatingCount = ViewRatingCount(listReviewPOI);
        oneStar.setProgress((Integer) viewRatingCount.get("star1percent"));
        twoStar.setProgress((Integer) viewRatingCount.get("star2percent"));
        thirtStar.setProgress((Integer) viewRatingCount.get("star3percent"));
        fourStar.setProgress((Integer) viewRatingCount.get("star4percent"));
        fiveStar.setProgress((Integer) viewRatingCount.get("star5percent"));
        countNumber5Star.setText(String.valueOf(viewRatingCount.get("star5")));
        countNumber4Star.setText(String.valueOf(viewRatingCount.get("star4")));
        countNumber3Star.setText(String.valueOf(viewRatingCount.get("star3")));
        countNumber2Star.setText(String.valueOf(viewRatingCount.get("star2")));
        countNumber1Star.setText(String.valueOf(viewRatingCount.get("star1")));
        totalVotingStar.setText(String.valueOf(viewRatingCount.get("totalCount")));
        totalVotingStarAverate.setText(String.valueOf(viewRatingCount.get("totalCountAverage")));
    }
    public void bindingViewReviewPop() {
        btnReview = findViewById(R.id.idbtReview);

    }
    private void bindingAction() {
        btnReview.setOnClickListener(this::onIdWriteReview);
    }
    public void bindingReviewPopup(View popupView) {
        btSummitReview = popupView.findViewById(R.id.btSummitReview);
        idTitleReview = popupView.findViewById(R.id.idTitleReview);
        idCommentReview = popupView.findViewById(R.id.idComment);
        ratingBar = popupView.findViewById(R.id.idratingBar);

    }
    private void onSubmitReview(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Sync("http://10.0.2.2:8080/ReviewPOI/Review", this);
        }
    }
    private void onIdWriteReview(View view) {
        generateReviewPopup();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void Sync(String url, Context context) {
        JSONObject jsonObjectRequest = new JSONObject();
        try{

        String accountString = sharedPreferenceHelper.getDataFromPref(context, "MyPref", "account");
        Gson gson = new Gson();

        String accountId = gson.fromJson(accountString, Account.class).getId();
        String Title = idTitleReview.getText().toString();
        String Comment = idCommentReview.getText().toString();
       myRating = (int) ratingBar.getRating();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            String date = sdf.format(c.getTime());
        HashMap<Integer, String> isVerified = CommentValidation.commentValidation(Title,Comment);
        for (int verified : isVerified.keySet()) {
            if (verified == 0) {
            jsonObjectRequest.put("rate", myRating);
            jsonObjectRequest.put("comment", Comment);
            jsonObjectRequest.put("title", Title);
            jsonObjectRequest.put("accountId", String.valueOf(accountId));
            jsonObjectRequest.put("poiID", poiID);
            jsonObjectRequest.put("timeCreate", date);
            } else {
                Toast.makeText(context, isVerified.get(verified), Toast.LENGTH_SHORT).show();
                return;
            }
        }

    } catch (JSONException e) {
        e.printStackTrace();
    }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, url, jsonObjectRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Intent i = new Intent(context, MainReview.class);
                        i.putExtra("poiID", poiID);
                        startActivity(i);
                        finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
            }
        }) {

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

        };
//        Volley.newRequestQueue(this).add(jsonObjReq);
        VolleySingleton.getmInstance(getApplicationContext()).addToRequestQueue(jsonObjReq);
//        VolleySingleton.getmInstance(getApplicationContext());
    }

    public void generateReviewPopup() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View popupView = getLayoutInflater().inflate(R.layout.activity_popup_rating_poi, null);

        // Input itinerary plan
        bindingReviewPopup(popupView);
        //datePicker();

        dialogBuilder.setView(popupView);
        alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

        // Button event
        btSummitReview.setOnClickListener(this::onSubmitReview);
    }

    public Dictionary ViewRatingCount(  ArrayList<ReviewPOI> listReviewPOI){
        int totalCount = 0;
        int star5 = 0;
        int star4 = 0;
        int star3 = 0;
        int star2 = 0;
        int star1 = 0;
        int averageStart = 0;
        for(int i=0; i<listReviewPOI.size();i++){
            if(listReviewPOI.get(i).getRate()==5){
                star5 += 1;
                totalCount += 1;
            }
            if(listReviewPOI.get(i).getRate()==4){
                star4 += 1;
                totalCount += 1;
            }
            if(listReviewPOI.get(i).getRate()==3){
                star3 += 1;
                totalCount += 1;
            }
            if(listReviewPOI.get(i).getRate()==2){
                star2 += 1;
                totalCount += 1;
            }
            if(listReviewPOI.get(i).getRate()==1){
                star1 += 1;
                totalCount += 1;
            }

        }
        Dictionary averageProcess = new Hashtable();
        if(listReviewPOI.size()== 0){
            averageProcess.put("star1percent", 0);
            averageProcess.put("star2percent", 0);
            averageProcess.put("star3percent", 0);
            averageProcess.put("star4percent", 0);
            averageProcess.put("star5percent", 0);
            averageProcess.put("totalCountAverage", 0);
        }else {
            averageProcess.put("star1percent", (star1 * 100) / totalCount);
            averageProcess.put("star2percent", (star2 * 100) / totalCount);
            averageProcess.put("star3percent", (star3 * 100) / totalCount);
            averageProcess.put("star4percent", (star4 * 100) / totalCount);
            averageProcess.put("star5percent", (star5 * 100) / totalCount);
            averageProcess.put("totalCountAverage", (star1*1 + star2*2+ star3*3 + star4*4+ star5*5)/totalCount);
        }
        averageProcess.put("star1", star1);
        averageProcess.put("star2", star2);
        averageProcess.put("star3", star3);
        averageProcess.put("star4", star4);
        averageProcess.put("star5", star5);
        averageProcess.put("totalCount", totalCount);
        return averageProcess;
    }

}