package com.application.isyara.data.repository

import android.content.Context
import android.net.Uri
import com.application.isyara.data.di.RetrofitDictionary
import com.application.isyara.data.local.DownloadedDictionary
import com.application.isyara.data.local.DownloadedDictionaryDao
import com.application.isyara.data.remote.ApiService
import com.application.isyara.utils.state.Result
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DictionaryRepository @Inject constructor(
    @RetrofitDictionary private val apiService: ApiService,
    @ApplicationContext private val context: Context,
    private val downloadedDictionaryDao: DownloadedDictionaryDao
) {
    private val client = OkHttpClient()

    fun getVideoList(): Flow<Result<List<String>>> = flow {
        emit(Result.Loading)
        try {
            val videoList = apiService.getDictionaryVideo()
            emit(Result.Success(videoList))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Terjadi kesalahan"))
        }
    }

    fun getAllDownloadedItems(): Flow<List<DownloadedDictionary>> {
        return downloadedDictionaryDao.getAllDownloadedItems()
    }

    suspend fun downloadItem(url: String, title: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                Timber.d("Starting download for $url")
                val request = Request.Builder().url(url).build()
                val response = client.newCall(request).execute()
                if (!response.isSuccessful) {
                    Timber.e("Download failed for $url with status code ${response.code}")
                    return@withContext false
                }

                val inputStream = response.body?.byteStream()
                if (inputStream == null) {
                    Timber.e("Response body is null for $url")
                    return@withContext false
                }

                val fileName =
                    Uri.parse(url).lastPathSegment ?: "video_${System.currentTimeMillis()}.mp4"
                val file = File(context.filesDir, fileName)
                Timber.d("Saving video to ${file.absolutePath}")
                val outputStream = FileOutputStream(file)
                inputStream.use { input ->
                    outputStream.use { output ->
                        input.copyTo(output)
                    }
                }
                val downloadedItem = DownloadedDictionary(
                    url = url,
                    title = title,
                    localPath = file.absolutePath
                )
                downloadedDictionaryDao.insertDownloadedItem(downloadedItem)
                Timber.d("Download and save succeeded for $url")
                true
            } catch (e: IOException) {
                Timber.e(e, "IOException during download of $url")
                false
            }
        }
    }

    suspend fun getLocalPath(url: String): String? {
        val downloadedItem = downloadedDictionaryDao.getDownloadedItemByUrl(url)
        return downloadedItem?.localPath
    }

    suspend fun deleteDownloadedItem(url: String): Boolean {
        return withContext(Dispatchers.IO) {
            val downloadedItem = downloadedDictionaryDao.getDownloadedItemByUrl(url)
            if (downloadedItem != null) {
                val file = File(downloadedItem.localPath)
                if (file.exists()) {
                    if (file.delete()) {
                        downloadedDictionaryDao.deleteDownloadedItem(downloadedItem)
                        Timber.d("Deleted video: $downloadedItem")
                        return@withContext true
                    } else {
                        Timber.e("Failed to delete file at " + downloadedItem.localPath)
                    }
                } else {
                    Timber.e("File does not exist at " + downloadedItem.localPath)
                }
            } else {
                Timber.e("No downloaded item found for URL: $url")
            }
            false
        }
    }

    suspend fun deleteAllDownloadedItems() {
        downloadedDictionaryDao.deleteAllDownloadedItems()
    }
}
