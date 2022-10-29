package tr.mobileapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import tr.mobileapp.Adapter.Recyclerview_adapter;
import tr.mobileapp.Entity.ReviewPOI;
import tr.mobileapp.R;

public class MainReviewTwo extends AppCompatActivity {

    RecyclerView recyclerview;

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
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();

        String response = intent.getStringExtra("response");
        JSONArray jsonObj;

        ArrayList<ReviewPOI> listReviewPOI = new ArrayList<>();

        try {
            jsonObj = new JSONArray(response);
            for (int i = 0; i < jsonObj.length(); i++) {
                ReviewPOI rvp = new ReviewPOI();
                rvp.setRatingId(jsonObj.getJSONObject(i).getInt("ratingId"));
                rvp.setRate(jsonObj.getJSONObject(i).getInt("rate"));
                rvp.setComment(jsonObj.getJSONObject(i).getString("comment"));
                JSONObject Acount = jsonObj.getJSONObject(i).getJSONObject("account");
                rvp.setAccountName(Acount.getString("fullName"));
                listReviewPOI.add(rvp);
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
//    public Dictionary ViewRatingProcess(  ArrayList<ReviewPOI> listReviewPOI){
//     int totalProcess = 0;
//     int star5 = 0;
//     int star4 = 0;
//     int star3 = 0;
//     int star2 = 0;
//     int star1 = 0;
//
//       for(int i=0; i<listReviewPOI.size();i++){
//           if(listReviewPOI.get(i).getRate()==5){
//               star5 += 5;
//               totalProcess += 5;
//           }
//           if(listReviewPOI.get(i).getRate()==4){
//               star4 += 4;
//               totalProcess += 4;
//           }
//           if(listReviewPOI.get(i).getRate()==3){
//               star3 += 3;
//               totalProcess += 3;
//           }
//           if(listReviewPOI.get(i).getRate()==2){
//               star2 += 2;
//               totalProcess += 2;
//           }
//           if(listReviewPOI.get(i).getRate()==1){
//               star1 += 1;
//               totalProcess += 1;
//           }
//       }
//        Dictionary averageProcess = new Hashtable();
//        averageProcess.put("star1", star1);
//        averageProcess.put("star2", star2);
//        averageProcess.put("star3", star3);
//        averageProcess.put("star4", star4);
//        averageProcess.put("star5", star5);
//        averageProcess.put("totalProcess", totalProcess);
//        return averageProcess;
//    }
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
        averageProcess.put("star1percent", (star1*100)/totalCount);
        averageProcess.put("star2percent", (star2*100)/totalCount);
        averageProcess.put("star3percent", (star3*100)/totalCount);
        averageProcess.put("star4percent", (star4*100)/totalCount);
        averageProcess.put("star5percent", (star5*100)/totalCount);
        averageProcess.put("star1", star1);
        averageProcess.put("star2", star2);
        averageProcess.put("star3", star3);
        averageProcess.put("star4", star4);
        averageProcess.put("star5", star5);
        averageProcess.put("totalCount", totalCount);
        averageProcess.put("totalCountAverage", (star1*1 + star2*2+ star3*3 + star4*4+ star5*5)/totalCount);

        return averageProcess;
    }

}