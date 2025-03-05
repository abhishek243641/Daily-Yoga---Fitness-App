package com.saxenna.abhi_AlignedAsana;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ThirdActivity extends AppCompatActivity {
    String buttonvalue;
    private  TextView countdown_text;
    private  Button countdoun_button;
    private  CountDownTimer countDownTimer;
    private long timeLeftInMillSeconds =60000;
    private boolean timeRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Intent intent = getIntent();
        buttonvalue = intent.getStringExtra("value");

        int intvalue = Integer.valueOf(buttonvalue);

        switch (intvalue) {
            case 1:
                setContentView(R.layout.bow);

                break;

            case 2:
                setContentView(R.layout.bridege);
                break;
            case 3:
                setContentView(R.layout.chair);
                break;
            case 4:
                setContentView(R.layout.child);
                break;
            case 5:
                setContentView(R.layout.cobler);
                break;
            case 6:
                setContentView(R.layout.cow);
                break;
            case 7:
                setContentView(R.layout.playji);
                break;
            case 8:
                setContentView(R.layout.pauseji);
                break;
            case 9:
                setContentView(R.layout.plank);
                break;
            case 10:
                setContentView(R.layout.cunches);
                break;
            case 11:
                setContentView(R.layout.situp);
                break;
            case 12:
                setContentView(R.layout.rotation);
                break;
            case 13:
                setContentView(R.layout.twist);
                break;
            case 14:
                setContentView(R.layout.windmill);
                break;
            case 15:
                setContentView(R.layout.legpose);
                break;


        }
        try {
            countdown_text =findViewById(R.id.countdown_text);
            countdoun_button = findViewById(R.id.counttime_btn);
        }catch (Exception e){
            e.printStackTrace();
        }

        countdoun_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startstop();
            }
        });

        updateTimer();


    }
    public void  startstop(){
        if (timeRunning){
            stopTimer();

        }else {
            startTimer();
        }
    }

    public  void startTimer(){
        countDownTimer = new CountDownTimer(timeLeftInMillSeconds,1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMillSeconds = l;
                updateTimer();
            }

            @Override
            public void onFinish() {
                int newvalue = Integer.valueOf(buttonvalue) +1;
                if (newvalue <= 7){
                    Intent intent = new Intent(ThirdActivity.this,ThirdActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value" , String.valueOf(newvalue));
                    startActivity(intent);
                }else {
                    newvalue = 1;
                    Intent intent = new Intent(ThirdActivity.this,ThirdActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value" , String.valueOf(newvalue));
                    startActivity(intent);
                }




            }
        }.start();
        countdoun_button.setText("Pause");
        timeRunning = true;
    }
    public void stopTimer(){
        countDownTimer.cancel();
        countdoun_button.setText("Start");
        timeRunning=false;
    }
    public void updateTimer(){
        int minutes = (int) timeLeftInMillSeconds /60000;
        int seconds = (int) timeLeftInMillSeconds % 60000 /1000;
        String timeLeftText;
        timeLeftText ="" + minutes;
        timeLeftText += ":";
        if (seconds <10) timeLeftText += "0";
        timeLeftText += seconds;

        countdown_text.setText(timeLeftText);


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("time" ,timeLeftInMillSeconds);
    }

    /**
     * This method is called after {@link #onStart} when the activity is
     * being re-initialized from a previously saved state, given here in
     * <var>savedInstanceState</var>.  Most implementations will simply use {@link #onCreate}
     * to restore their state, but it is sometimes convenient to do it here
     * after all of the initialization has been done or to allow subclasses to
     * decide whether to use your default implementation.  The default
     * implementation of this method performs a restore of any view state that
     * had previously been frozen by {@link #onSaveInstanceState}.
     *
     * <p>This method is called between {@link #onStart} and
     * {@link #onPostCreate}. This method is called only when recreating
     * an activity; the method isn't invoked if {@link #onStart} is called for
     * any other reason.</p>
     *
     * @param savedInstanceState the data most recently supplied in {@link #onSaveInstanceState}.
     * @see #onCreate
     * @see #onPostCreate
     * @see #onResume
     * @see #onSaveInstanceState
     */
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        timeLeftInMillSeconds = savedInstanceState.getLong("time");
        countdown_text.setText(String.valueOf(timeLeftInMillSeconds));
        updateTimer();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}