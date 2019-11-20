package com.example.evenlypoi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.poi_row.*
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





        //imageView_share.setOnClickListener(){


        searchPoisUrl()
    }

    fun searchPoisUrl(category: String = ""){

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

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: okhttp3.Response) {
                val body = response.body?.string()
                println(body)

                val gson = GsonBuilder().create()
                gson.fromJson(body, HomeFeed::class.java)

            val homeFeed = gson.fromJson(body, HomeFeed::class.java)

                runOnUiThread {
                    recyclerView_main.adapter = MainAdapter(homeFeed.response.venues)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })

    }
}


