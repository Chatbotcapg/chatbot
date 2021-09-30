package com.example.chatbotapp.presenter

import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterFragmentPresenter(val view: View) {
    val fAuth: FirebaseAuth

    //run in init to make sure the reference exists whenever needed in class
    init {
        fAuth = FirebaseAuth.getInstance()
    }

    // make sure password is atleast 6 characters long. isNullOrEmpty prevents Null Pointer Exception
    fun validatePassword(password: String): Boolean {
        if (password.isNullOrEmpty() || password.length < 6) {
            view.showToast("password must be at-least 6 characters")
            return false
        } else
            return true
    }

    //validates email in the sdame way as password.Prevents NPE
    fun validateEmail(email: String): Boolean {
        if (email.isNullOrEmpty() || email.length < 6) {
            view.showToast("Enter a valid email")
            return false
        } else {
            return true
        }
    }


    //once email and password are validated,try and create new user with credentials(move to FirebaseWrapper())
    //if user creation is successful, save the data to a new user with StoreInfoOfCreatedUser()
    fun createFirebaseUserWithEmailAndPassword(
        name: String,
        email: String,
        password: String,
        navigate: () -> (Unit)
    ) {
        fAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    //store new user data into firebase
                    Log.d("register", "success")
                    view.showToast("Register Successful")
                    navigate()

                } else {
                    view.showToast("${it.exception?.message}")
                    Log.d(
                        "register",
                        "fail :${it.exception?.message}"
                    )
                }
            }

    }



    interface View {

        fun showToast(message: String?)
    }
}