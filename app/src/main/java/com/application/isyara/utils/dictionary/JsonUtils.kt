package com.application.isyara.utils.dictionary

import android.content.Context
import kotlinx.serialization.json.Json

object JsonUtils {
    private val json = Json { ignoreUnknownKeys = true }

    fun getPictureList(context: Context, fileName: String = "kamusGambar.json"): List<String> {
        return try {
            val jsonString = context.assets.open(fileName)
                .bufferedReader()
                .use { it.readText() }
            json.decodeFromString(jsonString)
        } catch (e: Exception) {
            emptyList()
        }
    }
}