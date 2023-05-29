package com.example.tripplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.tripplanner.app_screen.ForgetActivity
import com.example.tripplanner.app_screen.ListActivity
import com.example.tripplanner.app_screen.RegisterActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {
    var txtEmail:EditText? = null
    var txtPassword:EditText? = null
    var btnLogin:Button? = null
    var btnRegister:Button? = null
    var txtForgetPassword:TextView? = null

    private lateinit var auth: FirebaseAuth;


    lateinit var email:String
    lateinit var password:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.title = "Bai Cafe (Coffee)"

        txtEmail = findViewById<EditText>(R.id.txtEmailCreate)
        txtPassword = findViewById<EditText>(R.id.txtpasswordCreate)
        btnLogin = findViewById<Button>(R.id.btnLogin)
        btnRegister = findViewById<Button>(R.id.btnRegisterCreate)
        txtForgetPassword = findViewById<TextView>(R.id.txtForgetPassword)

        auth = FirebaseAuth.getInstance()

        btnRegister!!.setOnClickListener {
            var intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnLogin!!.setOnClickListener {
            LoginEmail()
        }

        txtForgetPassword!!.setOnClickListener {
            var forgetintent = Intent(this, ForgetActivity::class.java)
            startActivity(forgetintent)
        }

    }

    //ฟังชั่นเมื่อเริ่มทำงาน อยู๋ใน document firebase
    override fun onStart() {
        super.onStart()
        val currentUser = auth!!.currentUser
        updateUI(currentUser)
    }

     private fun LoginEmail() {
         email = txtEmail!!.text.toString()
         password = txtPassword!!.text.toString()
         auth!!.signInWithEmailAndPassword(email,password).addOnCompleteListener(this) {
                 task -> if (task.isSuccessful){
             Log.d("MyApp","Create New User Success!")
             val user = auth!!.currentUser
             updateUI(user)
         }else{
             Log.w("MyApp","Failure Process!",task.exception)
             Toast.makeText(this@MainActivity,"Authentication Failed.", Toast.LENGTH_SHORT).show()
             updateUI(null)
         }
         }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null){
            val uId = user.uid
            val email = user.email
            Toast.makeText(this@MainActivity,"Welcome: $email you ID is $uId",Toast.LENGTH_SHORT).show()
            val intentSession = Intent(this, ListActivity::class.java)
            startActivity(intentSession)
        }
    }
}