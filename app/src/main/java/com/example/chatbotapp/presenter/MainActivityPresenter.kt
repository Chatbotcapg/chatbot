package com.example.chatbotapp.presenter

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat

class MainActivityPresenter(val view1: MainView) {
    // Code checks for Location Permission, If permission doesnt exist,request it

    interface MainView {
        fun showToast(message: String?)
    }
}