package com.example.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onRegister(View view) {




        Intent i = new Intent(LoginActivity.this , RegisterActivity.class);
        startActivity(i);
    }

    public void onLogin(View view) {
        Intent i = new Intent(LoginActivity.this , MainActivity.class);
        startActivity(i);

    }
}
