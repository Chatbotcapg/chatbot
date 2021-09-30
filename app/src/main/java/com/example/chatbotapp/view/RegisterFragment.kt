package com.example.chatbotapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chatbotapp.R

import android.content.Intent
import android.util.Log
import android.widget.*
import androidx.navigation.findNavController
import com.example.chatbotapp.presenter.RegisterFragmentPresenter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment(), RegisterFragmentPresenter.View {

    lateinit var presenter: RegisterFragmentPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = RegisterFragmentPresenter(this)
        rsignB.setOnClickListener {
            it.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        registerB.setOnClickListener {
            onRegisterClick()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    //registers a new user and moves to next activity
    private fun onRegisterClick() {

        val name = rnameE.text.toString().trim()
        val email = remailE.text.toString().trim()
        val pass = rpassE.text.toString().trim()
        //validateEmail
        val emailvalidated = presenter.validateEmail(email)
        Log.d("validated", emailvalidated.toString())
        val passwordvalidated = presenter.validatePassword(pass)
        Log.d("password validated", passwordvalidated.toString())
        //create user with email and password
        if ((emailvalidated && passwordvalidated)) {
            presenter.createFirebaseUserWithEmailAndPassword(name, email, pass) {
            }
        }

    }

    override fun showToast(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }


}