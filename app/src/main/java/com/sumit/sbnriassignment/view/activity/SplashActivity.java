package com.sumit.sbnriassignment.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.sumit.sbnriassignment.R;
import androidx.appcompat.app.AppCompatActivity;



/**
 * This is splash screen activity
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        launchNextActivity();
    }

    /**
     * this method check is user logged in or not and launch login or home activity after 3 seconds time.
     */
    public void launchNextActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);

    }


}
