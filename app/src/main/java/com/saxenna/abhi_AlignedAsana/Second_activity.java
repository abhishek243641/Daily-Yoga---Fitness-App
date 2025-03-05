package com.saxenna.abhi_AlignedAsana;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowInsets;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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


public class Second_activity extends AppCompatActivity {
    int [] newArray;
    InterstitialAd interstitial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
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


        AdView adView2 =findViewById(R.id.adView2);

        MobileAds.initialize(this);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        adView2.loadAd(adRequest2);


        AdView adView9 =findViewById(R.id.adView9);

        MobileAds.initialize(this);
        AdRequest adRequest9 = new AdRequest.Builder().build();
        adView9.loadAd(adRequest9);

        AdView adView3 =findViewById(R.id.adView3);

        MobileAds.initialize(this);
        AdRequest adRequest3 = new AdRequest.Builder().build();
        adView3.loadAd(adRequest3);

        AdRequest adRequest4 = new AdRequest.Builder().build();

        InterstitialAd.load(this, getString(R.string.inter_ad_id), adRequest4, new InterstitialAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Log.e("Error" ,loadAdError.toString());
            }

            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                super.onAdLoaded(interstitialAd);
                interstitial = interstitialAd;

                interstitial.setFullScreenContentCallback(new FullScreenContentCallback() {
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
                        interstitial = null;
                    }
                });

            }
        });


  new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
          if (interstitial != null)
              interstitial.show(Second_activity.this);
          else
              Log.e("Adpending" ,"Ad is not ready");
      }
  },5000) ;






        Toolbar toolbar = findViewById(R.id.toolbare);
        setSupportActionBar(toolbar);

        newArray = new int[]{
                R.id.bow_pose,R.id.bridge_pose ,R.id.chair_pose , R.id.child_pose ,R.id.cobler_pose ,R.id.cow_pose ,R.id.playji_pose ,R.id.pauseji_pose ,
                R.id.plank_pose ,R.id.crunches_pose , R.id.situp_pose ,R.id.rotation_pose ,R.id.twist_pose , R.id.windmill_pose,R.id.legup_pose ,




        };

    }

    public void Imagebuttononclick(View view) {
        for (int i =0; i<newArray.length; i++){
            if (view.getId() == newArray[i]){
                int value = i+1;
                Log.i("FIRST",String.valueOf(value));
                Intent intent =new Intent(Second_activity.this ,ThirdActivity.class);
                intent.putExtra("value" , String.valueOf(value));
                startActivity(intent);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main ,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.id_rate){
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" +getPackageName())));
            }catch (Exception ex){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+ getPackageName())));
            }
            return true ;
        }
        if (id == R.id.id_share){
            Intent myintent =new Intent(Intent.ACTION_SEND);
            myintent.setType("text/plain");
            String sharbody = "This is the best app for yoga \n this is free app downlod now..\n" +"https://play.google.com/store/apps/details?id=com.example.abhi_AlignedAsana&hl=en" ;
            String sharehub = "Daily Yoga";
            myintent.putExtra(Intent.EXTRA_SUBJECT , sharehub);
            myintent.putExtra(Intent.EXTRA_TEXT, sharbody);
            startActivity(Intent.createChooser(myintent ,"share using"));

            return true ;
        }
        return true;
    }
}