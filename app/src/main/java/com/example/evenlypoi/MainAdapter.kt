package com.example.evenlypoi

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.poi_row.view.*

class MainAdapter(val activity: AppCompatActivity, val poi: List<Venue>): RecyclerView.Adapter<CustomViewHolder>(){

    // number of POIs being shown
    override fun getItemCount(): Int {
        return 20
    }

    // init rows of POI
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.poi_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    // set up row
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        holder.view.textView_poiTitle.text = poi[position].name
        holder.view.textView_poiCategory.text = poi[position].categories!![0].shortName
        holder.view.textView_poiDistance.text = poi[position].location!!.distance + "m from evenly"

        // change icon
        Glide.with(activity)
            .load(poi[position].categories!![0].icon.prefix + "32" +
                    poi[position].categories!![0].icon.suffix)
            .into(holder.view.imageView_icon)

        // set up sharing message
        var message = "Hey, I want to share this place with you\n" +
                "https://foursquare.com/v/" + poi[position].id

        // share POI
        holder.view.imageView_share.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT, message)
            intent.type = "text/plain"
            holder.view.imageView_share.context.startActivity(intent)
        }

        // open POI in Google Maps
        holder.view.imageView_maps.setOnClickListener{
            val uri = Uri.parse("geo:0,0?q=" + poi[position].name + ", " +
                    poi[position].location!!.address + ", Berlin")
            val intent = Intent(Intent.ACTION_VIEW)
            intent.apply { data = uri }
            intent.setPackage("com.google.android.apps.maps")
            //intent.type = "text/plain"
            holder.view.imageView_maps.context.startActivity(intent)
        }
    }
}


class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}