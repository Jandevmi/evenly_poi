package com.example.evenlypoi

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.poi_row.view.*

class MainAdapter(val poi: List<Venue>): RecyclerView.Adapter<CustomViewHolder>(){

    override fun getItemCount(): Int {
        return 12
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.poi_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view.textView_poiTitle.text = poi[position].name
        holder.view.textView_poiCategory.text = poi[position].categories!![0].shortName
        holder.view.textView_poiDistance.text = poi[position].location!!.distance + " m"

        var message = "Hey, I want to share this place with you\n" +
                "https://foursquare.com/v/" + poi[position].id
        
        holder.view.imageView_share.setOnClickListener(){
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, message)
            intent.type = "text/plain"
            holder.view.imageView_share.context.startActivity(intent)
        }
    }
}


class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}