package com.saxenna.abhi_AlignedAsana;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class MainActivity extends AppCompatActivity {
    Button button1 ,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
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





        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button1 = findViewById(R.id.btnyoga1);
        button2 = findViewById(R.id.btnyoga2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this ,Second_activity.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this ,Second_activity.class);
                startActivity(intent);
            }
        });






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

    public void beforeage18(View view) {

        Intent intent = new Intent(MainActivity.this,Second_activity.class);
        startActivity(intent);



    }

    public void afterage18(View view) {
        Intent intent = new Intent(MainActivity.this,Second_activity.class);
        startActivity(intent);
    }

    public void food(View view) {
        Intent intent = new Intent(MainActivity.this,Foodactivity.class);
        startActivity(intent);
        finish();

    }


}