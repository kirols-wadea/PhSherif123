package com.lero.phsherif.ui.auth;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.lero.phsherif.R;

public class RegisterActivity extends AppCompatActivity {
    EditText nameEditText, emailEditText, phoneEditText, passwordEditText;
    Button registerButton;

    private FirebaseAuth firebaseAuth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // ربط عناصر الواجهة من ملف XML
        EditText nameEditText = findViewById(R.id.etName);
        EditText emailEditText = findViewById(R.id.etEmail);
        EditText phoneEditText = findViewById(R.id.etPhone);
        EditText passwordEditText = findViewById(R.id.etPassword);
        Button registerButton = findViewById(R.id.btnRegister);
        // Fire base
        FirebaseAuth firebaseAuth;

        // معالجة النقر على زر التسجيل
        registerButton.setOnClickListener(view -> {
            // قراءة البيانات المدخلة
            String name = nameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String phone = phoneEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();



            // التحقق من صحة البيانات المدخلة
            if (validateInputs(name, email, phone, password)) {
                // عرض رسالة نجاح
                Toast.makeText(RegisterActivity.this, "Registration Successful!", Toast.LENGTH_LONG).show();
                // يمكنك هنا استبدال الرسالة بإجراء عملية التسجيل الحقيقية
            }
        });
    }

    /**
     * التحقق من صحة البيانات المدخلة
     */
    private boolean validateInputs(String name, String email, String phone, String password) {
        // التحقق من الاسم
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            return false;
        }

        // التحقق من البريد الإلكتروني
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
            return false;
        }

        // التحقق من رقم الهاتف
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!phone.matches("^\\d{10,15}$")) {
            Toast.makeText(this, "Please enter a valid phone number (10-15 digits)", Toast.LENGTH_SHORT).show();
            return false;
        }

        // التحقق من كلمة المرور
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return false;

        }

        return true; // إذا كانت جميع الحقول صحيحة



    }

    private void register(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCanceledListener(() -> {

                });
    }
}


