package com.saxenna.abhi_AlignedAsana;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;


public class Foodactivity extends AppCompatActivity {
    ListView listView;
   InterstitialAd mInterstial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_foodactivity);
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


        TextView textView = findViewById(R.id.tipid);


        AdView adView4 =findViewById(R.id.adView4);

        MobileAds.initialize(this);
        AdRequest adRequest4 = new AdRequest.Builder().build();
        adView4.loadAd(adRequest4);


        AdRequest adRequest8 = new AdRequest.Builder().build();

        InterstitialAd.load(this, getString(R.string.inter_id2), adRequest8, new InterstitialAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Log.e("Error" ,loadAdError.toString());
            }

            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                super.onAdLoaded(interstitialAd);
                mInterstial = interstitialAd;

                mInterstial.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdClicked() {
                        super.onAdClicked();
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent();
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                        super.onAdFailedToShowFullScreenContent(adError);
                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent();
                        mInterstial = null;
                    }
                });

            }
        });


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mInterstial != null)
                    mInterstial.show(Foodactivity.this);
                else
                    Log.e("Adpending" ,"Ad is not ready");
            }
        },5000) ;








        String[] tstory = getResources().getStringArray(R.array.title_stroy);
        final  String[] dstory = getResources().getStringArray(R.array.details_storyy);


        listView = findViewById(R.id.list);

        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,R.layout.row,R.id.rowtext,tstory);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String t = dstory[position];
                Intent intent = new Intent(Foodactivity.this,FoodactivityDeatils.class);
                intent.putExtra("story" , t);
                startActivity(intent);
            }
        });
    }



    public void Foodgoback(View view) {
        Intent intent = new Intent(Foodactivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}