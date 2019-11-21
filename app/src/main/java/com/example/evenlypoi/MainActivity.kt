package com.example.evenlypoi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {

    var clientId = "1VL3KZB33TEC4VUE44SIQI33WZCRFBKMRIYW0DGW4JPPVMEL"
    var clientSecret = "NWZG4MJGQEKZEY1M2ZK1UIQXADSNTGO1HDZJ5HZWNNFF1B2Y"
    var version = "20191120"
    var location = "52.500342, 13.425170"
    val searchRadius = "200"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView_main.layoutManager = LinearLayoutManager(this)

        showPois()
    }

    //Todo
    //Add onClick for Icons to slected POIs by category

    fun showPois(){

        //ToDo
        //Implement category as an optimal parameter

        val client = OkHttpClient()
        val url = HttpUrl.Builder()
            .scheme("https")
            .host("api.foursquare.com")
            .addPathSegment("v2")
            .addPathSegment("venues")
            .addPathSegment("search")
            .addQueryParameter("client_id", clientId)
            .addQueryParameter("client_secret", clientSecret)
            .addQueryParameter("v", version)
            .addQueryParameter("intent", "checkin")
            .addQueryParameter("radius", searchRadius)
            .addQueryParameter("ll", location)
            .build()

        val request = Request.Builder().url(url).build()
        println("Build URL: " + url.toString())
        println("Build Request: " + request.toString())


        // need
        val activity = this
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: okhttp3.Response) {
                val body = response.body?.string()
                println(body)

                // convert from json to homefeed
                val gson = GsonBuilder().create()
                gson.fromJson(body, HomeFeed::class.java)
                val homeFeed = gson.fromJson(body, HomeFeed::class.java)

                //ToDO
                //Drop Evenly HQ from List
                //Sort List by distance

                // set up recyclerView
                runOnUiThread {
                    recyclerView_main.adapter = MainAdapter(activity, homeFeed.response.venues)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })

    }
}


