package com.example.tripplanner.app_screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.tripplanner.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

class ContentBakeryActivity : AppCompatActivity() {

    lateinit var txtContentTitle :TextView
    lateinit var imageViewContent:ImageView
    lateinit var txtViewDetail:TextView

    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        txtContentTitle = findViewById(R.id.txtContentTitle)
        imageViewContent = findViewById(R.id.imageViewContent)
        txtViewDetail = findViewById(R.id.txtViewDetail)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance("https://tripplanner-b82d5-default-rtdb.firebaseio.com/")


        val intent = intent
        var getKey = intent.getStringExtra("Keys")

        var databaseReference = firebaseDatabase.getReference("bakery/$getKey")
        databaseReference.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                txtContentTitle.text = snapshot.child("title").value.toString()
                txtViewDetail.text = snapshot.child("detail").value.toString()
                Picasso.get().load(snapshot.child("image").value.toString())
                    .into(imageViewContent)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}