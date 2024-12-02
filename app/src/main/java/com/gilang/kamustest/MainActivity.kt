package com.gilang.kamustest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Replace with your public JSON URL
        val jsonUrl = "https://storage.googleapis.com/isyara_kamus_bucket/file-list.json"

        // Fetch the video URLs from the JSON file
        fetchVideoUrlsFromJson(jsonUrl) { videoUrls ->
            runOnUiThread {
                val adapter = VideoAdapter(videoUrls) // Pass the list of URLs directly
                recyclerView.adapter = adapter
            }
        }
    }

    /**
     * Fetches the video URLs from a JSON file located at the provided URL.
     * The callback will provide a list of video URLs upon successful fetching and parsing.
     */
    private fun fetchVideoUrlsFromJson(jsonUrl: String, callback: (List<String>) -> Unit) {
        val client = OkHttpClient()
        val request = Request.Builder().url(jsonUrl).build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.e("MainActivity", "Error fetching JSON", e)
            }

            override fun onResponse(call: okhttp3.Call, response: Response) {
                if (response.isSuccessful) {
                    val json = response.body?.string()
                    if (json != null) {
                        try {
                            // Parse the JSON file into a list of strings
                            val videoUrls: List<String> = Gson().fromJson(json, object : TypeToken<List<String>>() {}.type)
                            callback(videoUrls)
                        } catch (e: Exception) {
                            Log.e("MainActivity", "Error parsing JSON", e)
                        }
                    }
                }
            }
        })
    }
}
