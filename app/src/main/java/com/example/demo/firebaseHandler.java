package com.example.demo;

import com.google.firebase.database.FirebaseDatabase;

public class firebaseHandler extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
