package tr.mobileapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import tr.mobileapp.R;

public class MainReview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(tr.mobileapp.R.layout.activity_main_review);
        Intent intent = getIntent();
    }
}