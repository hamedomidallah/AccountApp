package com.example.accountapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText etEmail, etPassword;
    private Button btnLogin, btnRegister;
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Firebase auth instance
        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        tvStatus = findViewById(R.id.tvStatus);

        btnLogin.setOnClickListener(v -> login());
        btnRegister.setOnClickListener(v -> register());

        // اگر کاربری از قبل لاگین است، مستقیم برو به MainActivity
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            goToMain();
        }
    }

    private void login() {
        String email = etEmail.getText().toString().trim();
        String pass = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
            tvStatus.setText("Email و Password را وارد کنید.");
            return;
        }

        tvStatus.setText("Signing in...");
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        tvStatus.setText("ورود موفق");
                        goToMain();
                    } else {
                        tvStatus.setText("خطا در ورود: " + task.getException().getMessage());
                    }
                });
    }

    private void register() {
        String email = etEmail.getText().toString().trim();
        String pass = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass) || pass.length() < 6) {
            tvStatus.setText("ایمیل و پسورد (حداقل 6 کاراکتر) را وارد کنید.");
            return;
        }

        tvStatus.setText("Creating account...");
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        tvStatus.setText("ثبت نام موفق. وارد می‌شویم...");
                        goToMain();
                    } else {
                        tvStatus.setText("خطا در ثبت نام: " + task.getException().getMessage());
                    }
                });
    }

    private void goToMain() {
        Intent i = new Intent(LoginActivity.this, ProductListActivity.class);
        startActivity(i);
        finish();
    }
}