package tr.mobileapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import tr.mobileapp.Adapter.Recyclerview_adapter;
import tr.mobileapp.Entity.ReviewPOI;
import tr.mobileapp.R;

public class MainReviewTwo extends AppCompatActivity {

    RecyclerView recyclerview;

    Recyclerview_adapter adapter;

    List<ReviewPOI> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_review_two);


        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));


        list = new ArrayList<>();


        list.add(
                new ReviewPOI(
                        4.8,
                        "Awesome App!!",
                        "Sed ut perspiciatis uant iudm nde omnis iste natus error sit laudan tb eatae vitaae suant explicabo.",
                        "Dean Jones, 20 mins ago"
                ));

        list.add(
                new ReviewPOI(
                        3.5,
                        "Nice & Clean App",
                        "Sed ut perspiciatis uant iudm nde omnis iste natus error sit laudan tb eatae vitaae suant explicabo.",
                        "Sopia Smith, 2 hrs ago"
                ));

        list.add(
                new ReviewPOI(
                        3.5,
                        "Nice & Clean App",
                        "Sed ut perspiciatis uant iudm nde omnis iste natus error sit laudan tb eatae vitaae suant explicabo.",
                        "Sopia Smith, 2 hrs ago"));


        Recyclerview_adapter adapter = new Recyclerview_adapter(this, list);


        recyclerview.setAdapter(adapter);
        ;
    }
}