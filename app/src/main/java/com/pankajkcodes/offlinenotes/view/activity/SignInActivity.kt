package com.pankajkcodes.offlinenotes.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pankajkcodes.offlinenotes.databinding.ActivitySignInBinding
import com.pankajkcodes.offlinenotes.db.local.UserDatabase

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {
            doLogIn()
        }


        binding.signUpText.setOnClickListener {
            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(intent)

        }
    }

    private fun doLogIn() {

        val email1: String = binding.signInEmail.text.toString()
        val password1: String = binding.signInPass.text.toString()

        if (email1 == "" || password1 == "") {
            Toast.makeText(this@SignInActivity, "Can't Be Empty", Toast.LENGTH_LONG).show()
        } else {

            val db2 = UserDatabase.getDatabase(this.applicationContext)

            val userList = db2?.userDao?.getAll()
            val iterator = userList?.iterator()

            if (iterator != null) {
                while (iterator.hasNext()) {
                    val users = iterator.next()
                    if (email1 == users.email && password1 == users.pass) {

                        startActivity(Intent(this@SignInActivity,MainActivity::class.java))
                        Toast.makeText(this,"Logged In ${users.email}", Toast.LENGTH_LONG).show()
                        this.finish()
                    } else {
                        Toast.makeText(this,"Invalid Email or Password", Toast.LENGTH_LONG).show()
                    }

                }
            }
        }


    }

    override fun onStart() {
        super.onStart()
        val db2 = UserDatabase.getDatabase(this.applicationContext)

        val userList = db2?.userDao?.getAll()
        val iterator = userList?.iterator()

        if (iterator != null) {
            while (iterator.hasNext()) {
                val users = iterator.next()
                    startActivity(Intent(this@SignInActivity,MainActivity::class.java))
                    Toast.makeText(this,"Logged In ${users.email}", Toast.LENGTH_LONG).show()
                    this.finish()


            }
        }

    }


}