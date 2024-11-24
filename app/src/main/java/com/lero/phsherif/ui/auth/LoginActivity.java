package com.lero.phsherif.ui.auth;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.lero.phsherif.MainActivity;
import com.lero.phsherif.R;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText loginEmail, loginPassword;
    public Button loginButton;
    private FirebaseAuth firebaseAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // ربط العناصر من ملف XML
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);
        firebaseAuth = FirebaseAuth.getInstance();

        // الانتقال إلى شاشة التسجيل
        findViewById(R.id.creatAccount).setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        // التعامل مع زر تسجيل الدخول
        loginButton.setOnClickListener(v -> {
            String email = Objects.requireNonNull(loginEmail.getText()).toString().trim();
            String password = Objects.requireNonNull(loginPassword.getText()).toString().trim();

            if (validateInput(email, password)) {
                login(email, password);

            }
        });
    }

    // التحقق من صحة المدخلات
    private boolean validateInput(String email, String password) {
        if (email.isEmpty()) {
            loginEmail.setError("Please enter your email");
            return false;
        }
        if (password.isEmpty()) {
            loginPassword.setError("Please enter your password");
            return false;
        }
        if (password.length() < 6) {
            loginPassword.setError("Password must be at least 6 characters");
            return false;
        }
        return true;
    }

    // تسجيل الدخول باستخدام Firebase
    private void login(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // تسجيل الدخول بنجاح
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();

                        // حفظ حالة تسجيل الدخول
                        getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
                                .edit()
                                .putBoolean("isLoggedIn", true)
                                .apply();

                        // الانتقال إلى الشاشة الرئيسية
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // فشل تسجيل الدخول
                        Toast.makeText(this, "Login failed! " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
