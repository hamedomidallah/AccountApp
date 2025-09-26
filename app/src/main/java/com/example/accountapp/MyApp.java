package com.example.accountapp;

import android.app.Application;

import com.google.firebase.FirebaseApp;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // initialize Firebase (google-services.json باید موجود باشد)
        FirebaseApp.initializeApp(this);
    }
}