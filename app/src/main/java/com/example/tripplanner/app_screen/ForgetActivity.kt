package com.example.tripplanner.app_screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.tripplanner.R
import com.google.firebase.auth.FirebaseAuth

class ForgetActivity : AppCompatActivity() {
    lateinit var txtEmailForget:EditText
    lateinit var btnReset:Button
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget)

        auth = FirebaseAuth.getInstance()

        btnReset = findViewById<Button>(R.id.btnReset)
        txtEmailForget = findViewById<EditText>(R.id.txtEmailForget)

        btnReset!!.setOnClickListener {
            val email = txtEmailForget!!.text.toString()
            if (TextUtils.isEmpty(email)){
                Toast.makeText(applicationContext,"please Enter Your Email",Toast.LENGTH_SHORT).show()
            }else{
                auth!!.sendPasswordResetEmail(email).addOnCompleteListener {
                    task -> if (task.isSuccessful){
                        Toast.makeText(this@ForgetActivity,"Please Check Your Email",Toast.LENGTH_SHORT).show()
                    }else{
                    Toast.makeText(this@ForgetActivity,"Fail to Send Your Email",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
}