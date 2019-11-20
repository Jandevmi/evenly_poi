package com.example.evenlypoi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.poi_row.view.*

class MainAdapter(val poi: List<Venue>): RecyclerView.Adapter<CustomViewHolder>(){

    //val poiTitles = listOf<Venue>()
    //var pois = listOf<Venue>()

    override fun getItemCount(): Int {
        return 12
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.poi_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        var poiTitle = poi[position].name
        holder.view.textView_poiTitle.text = poiTitle
    }
}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}