package com.application.isyara.utils.settings

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

data class MultipartData(
    val part: MultipartBody.Part,
    val file: File
)

/**
 * Mengonversi [Uri] menjadi [MultipartData].
 *
 * @param context Context untuk mengakses ContentResolver.
 * @param partName Nama bagian form data.
 * @return [MultipartData] atau null jika terjadi kesalahan.
 */
fun Uri.toMultipartData(context: Context, partName: String): MultipartData? {
    return try {
        val contentResolver = context.contentResolver
        val mimeType = contentResolver.getType(this) ?: return null
        val extension = mimeTypeToExtension(mimeType) ?: return null

        val fileName = queryName(context) ?: "temp_image_${System.currentTimeMillis()}.$extension"
        val tempFile = File(context.cacheDir, fileName)
        contentResolver.openInputStream(this)?.use { inputStream ->
            FileOutputStream(tempFile).use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        } ?: return null

        val requestFile = tempFile.asRequestBody(mimeType.toMediaTypeOrNull())
        val multipartBody = MultipartBody.Part.createFormData(partName, tempFile.name, requestFile)

        MultipartData(multipartBody, tempFile)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

/**
 * Mengonversi [mimeType] menjadi ekstensi file yang sesuai.
 *
 * @param mimeType Tipe MIME dari file.
 * @return Ekstensi file atau null jika tidak dikenali.
 */
fun mimeTypeToExtension(mimeType: String): String? {
    return when (mimeType.lowercase()) {
        "image/jpeg", "image/jpg" -> "jpg"
        "image/png" -> "png"
        "image/gif" -> "gif"
        else -> null
    }
}

/**
 * Mengambil nama file asli dari [Uri].
 *
 * @param context Context untuk mengakses ContentResolver.
 * @return Nama file atau null jika tidak dapat diambil.
 */
@SuppressLint("Range")
fun Uri.queryName(context: Context): String? {
    val cursor = context.contentResolver.query(this, null, null, null, null)
    return cursor?.use {
        if (it.moveToFirst()) {
            it.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
        } else {
            null
        }
    }
}
