package com.example.chatbotapp.presenter

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.chatbotapp.ChatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginFragmentPresenter(val view: LoginView) {
    val fAuth: FirebaseAuth = FirebaseAuth.getInstance()


    // if email is null or empty,return false.This is to prevent Null Pointer Exception
    fun isEmailValid(username: String): Boolean {
        return !(username.isNullOrEmpty())
    }

    // if password is null or empty,return false.This is to prevent Null Pointer Exception
    fun isPasswordValid(password: String): Boolean {
        //       view.showToast("password is invalid")
        return !(password.isNullOrEmpty())
    }

    // once the email and password are validated in view, sign in with those credentials. navigate()
    // callback is called on success to navigate to DeliveryActivity
    fun signInFirebaseWithEmailAndPassword(
        email: String,
        password: String,
        navigate: () -> (Unit)
    ) {
        fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {

                Log.d("registermvp", "success")
                view.showToast("Login Success")
                navigate()
            } else {
                view.showToast("Error! ${it.exception?.message}")
                Log.d("register", "fail :: ${it.exception?.message}")
            }
        }
    }

    // send a reset email after email is validated
    interface LoginView {
        fun showToast(message: String?)
    }
}