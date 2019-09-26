package com.example.demo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
//    private FirebaseAuth mAuth;
//    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void onRegister(View view) {
        Intent i = new Intent(LoginActivity.this , RegisterActivity.class);
        startActivity(i);
    }

    public void onLogin(View view) {
        EditText email = (EditText)findViewById(R.id.email);
        EditText password = (EditText)findViewById(R.id.password);
//        mAuth.createUserWithEmailAndPassword(email.toString(), password.toString())
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "createUserWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
////                            updateUI(user);
//
//                            Intent i = new Intent(LoginActivity.this , MainActivity.class);
//                            i.putExtra("userId",user);
//                            startActivity(i);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                            Toast.makeText(LoginActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
////                            updateUI(null);
//                        }
//
//                        // ...
//                    }
//                });
                            Intent i = new Intent(LoginActivity.this , MainActivity.class);
//                            i.putExtra("userId",user);
                            startActivity(i);

    }
}
