package com.example.tripplanner.app_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.tripplanner.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    lateinit var txtEmailCreate:EditText
    lateinit var txtPasswordCreate:EditText
    lateinit var txtFirstnameCreate:EditText
    lateinit var btnLoginCreate:Button

    lateinit var email:String
    lateinit var password:String
    lateinit var firstName:String

    private lateinit var auth: FirebaseAuth;
    private lateinit var database: FirebaseDatabase;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        txtEmailCreate = findViewById<EditText>(R.id.txtEmailCreate)
        txtPasswordCreate = findViewById<EditText>(R.id.txtpasswordCreate)
        txtFirstnameCreate = findViewById<EditText>(R.id.txtFirstnameCreate)
        btnLoginCreate = findViewById<Button>(R.id.btnRegisterCreate)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance("https://tripplanner-b82d5-default-rtdb.firebaseio.com/")

        var test = database.getReference("SayHi")
        test.setValue("Hello,nomad")

        btnLoginCreate!!.setOnClickListener {
            createAccount()
        }

    }

     private fun createAccount() {
         email = txtEmailCreate!!.text.toString()
         password = txtPasswordCreate!!.text.toString()
         firstName = txtFirstnameCreate!!.text.toString()
         auth!!.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this) {
             task -> if (task.isSuccessful){
                 Log.d("MyApp","Create New User Success!")
             val user = auth!!.currentUser
             val databaseReference = database.reference.child("users").push()
             databaseReference.child("uid").setValue(user!!.uid)
             databaseReference.child("email").setValue(user!!.email)
             databaseReference.child("firstname").setValue(txtFirstnameCreate!!.text.toString())
             updateUI(user)
             }else{
                 Log.w("MyApp","Failure Process!",task.exception)
             Toast.makeText(this@RegisterActivity,"Authentication Failed.",Toast.LENGTH_SHORT).show()
             updateUI(null)
             }
         }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null){
            val uId = user.uid
            val email = user.email
            Toast.makeText(this@RegisterActivity,"Welcome: $email you ID is $uId",Toast.LENGTH_SHORT).show()
            val intentSession = Intent(this, ListActivity::class.java)
            startActivity(intentSession)
        }
    }
}