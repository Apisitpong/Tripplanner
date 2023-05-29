package com.example.tripplanner.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tripplanner.R
import com.example.tripplanner.ViewHolder
import com.example.tripplanner.app_screen.ContentActivity
import com.example.tripplanner.app_screen.ContentBakeryActivity
import com.example.tripplanner.model.CafeModel
import com.squareup.picasso.Picasso

class BakeryAdapter(val bakeryList:List<CafeModel>): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.itemvertical,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = bakeryList[position]
        holder.txtTitleItem.text = dataModel.title
        Picasso.get().load(dataModel.image)
            .into(holder.imageView)

        //card vertical
        holder.cardView.setOnClickListener(View.OnClickListener { view ->
            val readActivity = Intent(view.context, ContentActivity::class.java)
            readActivity.putExtra("Keys",dataModel.key)
            view.context.startActivity(readActivity)
            Log.d("TRip Planner",dataModel.title.toString())
        })
    }

    override fun getItemCount(): Int {
        return bakeryList.size
    }

}