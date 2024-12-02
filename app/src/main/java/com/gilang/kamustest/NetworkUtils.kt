package com.gilang.kamustest

import android.util.Log
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

fun fetchVideoUrlsFromJson(
    jsonUrl: String,
    onSuccess: (List<String>) -> Unit,
    onError: (Exception) -> Unit
) {
    val client = OkHttpClient()
    val request = Request.Builder().url(jsonUrl).build()

    client.newCall(request).enqueue(object : okhttp3.Callback {
        override fun onFailure(call: okhttp3.Call, e: IOException) {
            Log.e("NetworkUtils", "Network request failed", e)
            onError(e)
        }

        override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
            if (response.isSuccessful) {
                val json = response.body?.string()
                if (json != null) {
                    try {
                        // Parse the JSON array directly into a List of Strings
                        val videoUrls: List<String> = Gson().fromJson(json, Array<String>::class.java).toList()

                        // Pass the list of video URLs back using the onSuccess callback
                        onSuccess(videoUrls)
                    } catch (e: Exception) {
                        Log.e("NetworkUtils", "Error parsing JSON", e)
                        onError(e)
                    }
                } else {
                    Log.e("NetworkUtils", "Response body is null")
                    onError(IOException("Response body is null"))
                }
            } else {
                val errorMessage = "Request failed with code: ${response.code}"
                Log.e("NetworkUtils", errorMessage)
                onError(IOException(errorMessage))
            }
        }
    })
}
