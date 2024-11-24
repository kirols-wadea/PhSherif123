package com.lero.phsherif;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.lero.phsherif.ui.xSplashActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btnlogout).setOnClickListener(v -> {





            getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
                    .edit()
                    .putBoolean("isLoggedIn", false)
                    .apply();
                finish();

            startActivity(new Intent(MainActivity.this, xSplashActivity.class));
            finish();


        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}