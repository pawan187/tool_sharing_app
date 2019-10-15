package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
//    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }

    public void OnRegister(View view) {
        Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(i);
    }

    public void onLogin(View view) {
        EditText email = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.password);
//        Log.i("crede tials",email.getText().toString()+" "+password.getText().toString());
        String email1, password1;
        email1 = email.getText().toString();
        password1 = password.getText().toString();

        if (TextUtils.isEmpty(email1)) {
            Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password1)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email1, password1)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Login successful!"+" "+ mAuth.getUid(), Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            Bundle b = new Bundle();
                            b.putString("userid",mAuth.getUid());
                            intent.putExtras(b);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Login failed! Please try again later", Toast.LENGTH_LONG).show();
//                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}