package com.example.evenlypoi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.poi_row.view.*

class MainAdapter: RecyclerView.Adapter<CustomViewHolder>(){

    val poiTitles = listOf<String>("First", "Second", "3rd", "more")

    override fun getItemCount(): Int {
        return 12
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.poi_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        var poiTitle = poiTitles.get(3)
        if(position<3) {
            poiTitle = poiTitles.get(position)
        }
        holder.view.textView_poiTitle.text = poiTitle
    }
}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}