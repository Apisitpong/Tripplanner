package com.example.tripplanner

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
//ขั้นตอน3
//รับค่าจาก database
class ViewHolder (view: View):RecyclerView.ViewHolder(view){
    var txtTitleItem:TextView = view.findViewById(R.id.txtTitleItem)
    var imageView:ImageView = view.findViewById(R.id.imageView)
    var cardView:CardView = view.findViewById(R.id.cardView)

}
