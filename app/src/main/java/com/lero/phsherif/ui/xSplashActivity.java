package com.lero.phsherif.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.lero.phsherif.MainActivity;
import com.lero.phsherif.R;
import com.lero.phsherif.ui.auth.LoginActivity;

@SuppressLint("CustomSplashScreen")
public class xSplashActivity extends AppCompatActivity {

    private boolean isLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        // مدة عرض صفحة البداية (3 ثواني)
        new Handler().postDelayed( () -> {
            SharedPreferences sharedPreferences = getSharedPreferences("userShared", MODE_PRIVATE);
            if (isLogin) {
                // الانتقال إلى الصفحة الرئيسية
                Intent intent = new Intent(xSplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

                getSharedPreferences("userShared", MODE_PRIVATE);
            } else {
                getSharedPreferences("userShared ", MODE_PRIVATE);
            }

            // الانتقال إلى الصفحة الرئيسية
            Intent intent = new Intent(xSplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();


            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        }, 3000); // 3000 ميلي ثانية = 3 ثواني

        }




    }
