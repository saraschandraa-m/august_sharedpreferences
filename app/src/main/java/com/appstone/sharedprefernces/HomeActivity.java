package com.appstone.sharedprefernces;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class HomeActivity extends AppCompatActivity {

    ConstraintLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView mTvUsername = findViewById(R.id.tv_username);
        Button mBtnLogout = findViewById(R.id.btn_logout);
        Button mBtnShowAlert = findViewById(R.id.btn_alert);
        Button mBtnShowSnackbar = findViewById(R.id.btn_snackbar);
        root = findViewById(R.id.constraint_home);

        SharedPreferences prefManager = getApplicationContext().getSharedPreferences("SHARED", MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefManager.edit();

        String username = prefManager.getString("USERNAME", "");
        mTvUsername.setText(username);


        mBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("USERNAME", "");
                editor.putString("PASSWORD", "");
                editor.putBoolean("ISREMEMBERME", false);
                editor.apply();

                Toast.makeText(HomeActivity.this, "You have successfully logged out", Toast.LENGTH_LONG).show();

                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
            }
        });

        mBtnShowAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(HomeActivity.this)
                        .setTitle("Alert")
                        .setMessage("Are you sure you want to logout")
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setNeutralButton("Maybe Later", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
            }
        });

        mBtnShowSnackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(root, "Showing Snackbar", Snackbar.LENGTH_LONG).setAction("REDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
            }
        });
    }
}