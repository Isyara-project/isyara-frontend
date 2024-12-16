package com.application.isyara.data.repository

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject

class ModelDownloadRepository @Inject constructor(private val context: Context) {

    private val client = OkHttpClient()

    /**
     * Mengunduh file dari URL dan menyimpannya ke direktori internal aplikasi.
     *
     * @param url URL file yang akan diunduh.
     * @param fileName Nama file yang akan disimpan.
     * @throws IOException jika terjadi kesalahan saat mengunduh atau menyimpan file.
     */
    suspend fun downloadModel(
        url: String,
        fileName: String,
        onProgress: (progress: Int) -> Unit
    ) {
        withContext(Dispatchers.IO) {
            val request = Request.Builder().url(url).build()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Failed to download file: $response")

                val body = response.body
                val totalBytes = body.contentLength()
                var downloadedBytes = 0L

                val file = File(context.filesDir, fileName)
                body.byteStream().use { inputStream ->
                    FileOutputStream(file).use { outputStream ->
                        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
                        var bytesRead: Int
                        while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                            outputStream.write(buffer, 0, bytesRead)
                            downloadedBytes += bytesRead
                            val progress = if (totalBytes > 0) {
                                ((downloadedBytes * 100) / totalBytes).toInt()
                            } else {
                                0
                            }
                            onProgress(progress)
                        }
                    }
                }
            }
        }
    }


    /**
     * Memeriksa apakah file model sudah ada di penyimpanan internal.
     *
     * @param fileName Nama file yang akan diperiksa.
     * @return True jika file ada, False jika tidak.
     */
    fun isModelDownloaded(fileName: String): Boolean {
        val file = File(context.filesDir, fileName)
        return file.exists()
    }

    /**
     * Mendapatkan File objek untuk model yang diunduh.
     *
     * @param fileName Nama file model.
     * @return File objek jika ada, null jika tidak.
     */
    fun getModelFile(fileName: String): File? {
        val file = File(context.filesDir, fileName)
        return if (file.exists()) file else null
    }
}
