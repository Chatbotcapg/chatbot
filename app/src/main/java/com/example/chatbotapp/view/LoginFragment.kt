package com.example.chatbotapp.view
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.chatbotapp.ChatActivity
import com.example.chatbotapp.R
import com.example.chatbotapp.nameActivity
import com.example.chatbotapp.presenter.LoginFragmentPresenter
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment(), LoginFragmentPresenter.LoginView {


    lateinit var presenter: LoginFragmentPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = LoginFragmentPresenter(this)

        lLoginB.setOnClickListener {
            onLoginClicked()
        }
        //when register is clicked, navigate to Register fragment
        lregT.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    //validation and moving to next fragment
    private fun onLoginClicked() {

        val email = lnameE.text.toString().trim()
        val pass = lpassE.text.toString().trim()
        if (presenter.isEmailValid(email) && presenter.isPasswordValid(pass)) {
            presenter.signInFirebaseWithEmailAndPassword(email, pass) {
            }
            startActivity(Intent(this.activity, nameActivity::class.java))
        }

        }


    override fun showToast(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }



}