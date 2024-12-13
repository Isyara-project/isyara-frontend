package com.application.isyara.utils.settings

import android.content.Context
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

fun Uri.toMultipartBodyPart(context: Context, partName: String): MultipartBody.Part? {
    return try {
        val contentResolver = context.contentResolver
        val mimeType = contentResolver.getType(this) ?: "image/*"
        val extension = mimeTypeToExtension(mimeType) ?: return null

        val inputStream = contentResolver.openInputStream(this) ?: return null
        val fileName = "temp_image_${System.currentTimeMillis()}.$extension"
        val file = File(context.cacheDir, fileName)
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)
        outputStream.close()
        inputStream.close()

        val requestFile = file.asRequestBody(mimeType.toMediaTypeOrNull())
        MultipartBody.Part.createFormData(partName, file.name, requestFile)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun mimeTypeToExtension(mimeType: String): String? {
    return when (mimeType) {
        "image/jpeg" -> "jpg"
        "image/jpg" -> "jpg"
        "image/png" -> "png"
        else -> null
    }
}
