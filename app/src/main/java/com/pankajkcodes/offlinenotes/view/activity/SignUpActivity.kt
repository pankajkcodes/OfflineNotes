package com.pankajkcodes.offlinenotes.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.pankajkcodes.offlinenotes.databinding.ActivitySignUpBinding
import com.pankajkcodes.offlinenotes.db.local.UserDatabase
import com.pankajkcodes.offlinenotes.models.User
import java.util.*

class SignUpActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySignUpBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.signUpBtn.setOnClickListener {
            doRegister()
        }

        binding.signinText.setOnClickListener {
            val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
            startActivity(intent)
            finish()

        }
    }

    private fun doRegister() {
        val name1: String = binding.signUpName.text.toString()
        val email1: String = binding.signUpEmail.text.toString()
        val mobile1: String = binding.signUpMobile.text.toString()
        val pass1: String = binding.signUpPassword.text.toString()

        if (name1 == "" || email1 == "" || mobile1 == "" || pass1 == "") {
            Toast.makeText(this@SignUpActivity, "Can't Be Empty", Toast.LENGTH_LONG).show()
        } else {
            val user = User()
            user.name = name1
            user.email = email1
            user.mobile = mobile1
            user.pass = pass1

            val db1 = UserDatabase.getDatabase(this.applicationContext)
            db1?.userDao?.insertAll(user)

            Toast.makeText(this@SignUpActivity, "User Register : $name1", Toast.LENGTH_LONG).show()
            startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
            finish()
        }
    }
}