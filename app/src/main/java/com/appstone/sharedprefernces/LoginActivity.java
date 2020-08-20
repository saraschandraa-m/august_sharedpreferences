package com.appstone.sharedprefernces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText mEtUsername;
    private EditText mEtPassword;
    private CheckBox mChRememberMe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEtUsername = findViewById(R.id.et_email_address);
        mEtPassword = findViewById(R.id.et_password);
        mChRememberMe = findViewById(R.id.chk_remember);
        Button mBtnLogin = findViewById(R.id.btn_login);

        SharedPreferences prefManager = getApplicationContext().getSharedPreferences("SHARED", MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefManager.edit();

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mEtUsername.getText().toString();
                String password = mEtPassword.getText().toString();

                boolean isRememberMe = mChRememberMe.isChecked();

                if (isRememberMe) {
                    editor.putString("USERNAME", username);
                    editor.putString("PASSWORD", password);
                    editor.putBoolean("ISREMEMBERME", isRememberMe);

//                    editor.commit();
                    editor.apply();
                }
                moveToHomeScreen();
            }
        });

        boolean isAlreadyLoggedIn = prefManager.getBoolean("ISREMEMBERME", false);
        if (isAlreadyLoggedIn) {
            moveToHomeScreen();
        }
    }


    private void moveToHomeScreen() {
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
    }
}