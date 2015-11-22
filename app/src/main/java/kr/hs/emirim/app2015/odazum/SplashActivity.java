package kr.hs.emirim.app2015.odazum;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class SplashActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {

                SharedPreferences prefs = getSharedPreferences("odazum", MODE_PRIVATE);
                boolean mIsLogined = prefs.getBoolean("islogined", false);

                Intent intent;
                if (mIsLogined){
                    intent = new Intent( getApplicationContext(),MainActivity.class);

                }else {
                    intent = new Intent( getApplicationContext(), LoginActivity.class);
                }
                startActivity(intent);
                finish();

            }
        }, 2000);
    }
}
