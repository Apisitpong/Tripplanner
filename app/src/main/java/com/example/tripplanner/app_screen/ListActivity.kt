package com.example.tripplanner.app_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tripplanner.adapter.BakeryAdapter
import com.example.tripplanner.adapter.CafeAdapter
import com.example.tripplanner.MainActivity
import com.example.tripplanner.R
import com.example.tripplanner.model.CafeModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class ListActivity : AppCompatActivity() {

    lateinit var recycleView:RecyclerView //ของ recycleViewCafe
    lateinit var recycleViewBakery:RecyclerView

    private lateinit var auth: FirebaseAuth;
    private lateinit var database: FirebaseDatabase;
    //ดึงข้อมูลจาก RT-Database
    private lateinit var databaseReference: DatabaseReference //ของ recycleViewCafe
    private lateinit var databaseReferenceBakery: DatabaseReference
    //จาก Model
    private lateinit var responseCafe:MutableList<CafeModel>
    private lateinit var responseBakery:MutableList<CafeModel>
    private var cafeAdapter: CafeAdapter? = null
    private var bakeryAdapter: BakeryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        supportActionBar!!.title = "Bai Cafe (Coffee)"

        // show recycleView
        recycleView = findViewById(R.id.recycleViewCafe)
        recycleViewBakery = findViewById(R.id.recycleViewBakery)

        // Firebase
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance("https://tripplanner-b82d5-default-rtdb.firebaseio.com/")

        //ของ recycleViewCafe
        recycleView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        databaseReference = database.getReference("cafe/")
        responseCafe = mutableListOf()
        //สร้าง Adapter//ขั้นตอน1
        cafeAdapter = CafeAdapter(responseCafe as ArrayList<CafeModel>)
        recycleView.adapter = cafeAdapter

        //ของ recycleViewBakery
        recycleViewBakery.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        databaseReferenceBakery = database.getReference("bakery/")
        responseBakery = mutableListOf()
        bakeryAdapter = BakeryAdapter(responseBakery as ArrayList<CafeModel>)
        recycleViewBakery.adapter = bakeryAdapter

        onBindingFirebase()

    }

    private fun onBindingFirebase() {
        databaseReference.addChildEventListener(object :ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                responseCafe.add(snapshot.getValue(CafeModel::class.java)!!)
                cafeAdapter!!.notifyDataSetChanged()
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        databaseReferenceBakery.addChildEventListener(object :ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                responseBakery.add(snapshot.getValue(CafeModel::class.java)!!)
                bakeryAdapter!!.notifyDataSetChanged()
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    override fun onStart() {
            super.onStart()
            val currentUser = auth!!.currentUser
            updateUI(currentUser)
        }

        private fun updateUI(user: FirebaseUser?) {
            if (user != null){
                val uId = user.uid
                val email = user.email
                Toast.makeText(this@ListActivity,"Welcome: $email you ID is $uId", Toast.LENGTH_SHORT).show()
//                val intentSession = Intent(this,ListActivity::class.java)
//                startActivity(intentSession)
            }else{
                val intentSesSion = Intent(this, MainActivity::class.java)
                startActivity(intentSesSion)
            }
        }
}