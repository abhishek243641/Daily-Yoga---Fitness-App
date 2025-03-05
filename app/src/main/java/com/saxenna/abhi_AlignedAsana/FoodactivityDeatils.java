package com.saxenna.abhi_AlignedAsana;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class FoodactivityDeatils extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fooddeatils);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        View docoreview = getWindow().getDecorView();
        docoreview.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @NonNull
            @Override
            public WindowInsets onApplyWindowInsets(@NonNull View v, @NonNull WindowInsets insets) {
                int left =insets.getSystemWindowInsetLeft();
                int top =insets.getSystemWindowInsetTop();
                int right =insets.getSystemWindowInsetRight();
                int bottom =insets.getSystemWindowInsetBottom();

                v.setPadding(left,top,right,bottom);

                return insets.consumeSystemWindowInsets();
            }
        });
        AdView adView =findViewById(R.id.adView);

   MobileAds.initialize(this);
   AdRequest adRequest = new AdRequest.Builder().build();
   adView.loadAd(adRequest);



        textView =findViewById(R.id.txt);
        String dstory = getIntent().getStringExtra("story");
        textView.setText(dstory);


    }

    public void goback(View view) {
        Intent intent= new Intent(FoodactivityDeatils.this , Foodactivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent= new Intent(FoodactivityDeatils.this , Foodactivity.class);
        startActivity(intent);
        finish();
    }
}