package tr.mobileapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import tr.mobileapp.R;

public class ListActivity extends AppCompatActivity {

    private LinearLayout lay_one , lay_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lay_one = findViewById(R.id.lay_one);
        lay_two = findViewById(R.id.lay_two);


        lay_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),HomeActivity.class));

            }
        });

        lay_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),TripPlanActivity.class));

            }
        });


    }
}
