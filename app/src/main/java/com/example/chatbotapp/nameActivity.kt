package com.example.chatbotapp
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_name.*


class nameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)
        btnLogin.setOnClickListener {
            if (username.text.isNotEmpty()) {
                val user = username.text.toString()
                App.user = user
                startActivity(Intent(this@nameActivity, ChatActivity::class.java))
            } else {
                Toast.makeText(applicationContext,"Username should not be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
}