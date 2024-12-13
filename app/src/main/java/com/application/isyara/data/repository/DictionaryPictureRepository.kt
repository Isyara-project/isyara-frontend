package com.application.isyara.data.repository

import com.application.isyara.data.di.RetrofitDictionary
import com.application.isyara.data.local.DownloadDictionaryPictureDao
import com.application.isyara.data.local.DownloadedDictionaryPicture
import com.application.isyara.data.model.DictionaryPicture
import com.application.isyara.data.model.toDictionaryPicture
import com.application.isyara.data.remote.ApiService
import com.application.isyara.utils.state.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DictionaryPictureRepository @Inject constructor(
    @RetrofitDictionary private val apiService: ApiService,
    private val downloadDictionaryPictureDao: DownloadDictionaryPictureDao
) {

    /**
     * Mengambil gambar yang sudah diunduh untuk offline, jika tidak ada, ambil dari API.
     */
    suspend fun getDictionaryPictures(): Flow<Result<List<DictionaryPicture>>> = flow {
        emit(Result.Loading)
        val cachedPictures = downloadDictionaryPictureDao.getAllDownloadedPictures()

        if (cachedPictures.isNotEmpty()) {
            emit(Result.Success(cachedPictures.map { it.toDictionaryPicture() }))
        } else {
            try {
                val apiPictures = apiService.getDictionaryPicture()
                if (apiPictures.isNotEmpty()) {
                    val downloadedPictures = apiPictures.mapIndexed { index, url ->
                        val imageName = url.split("/").last().split(".")
                            .first()
                        DownloadedDictionaryPicture(
                            word = imageName,
                            imageUrl = url,
                            definition = "No definition"
                        )
                    }
                    downloadDictionaryPictureDao.insertPictures(downloadedPictures)

                    emit(Result.Success(apiPictures.map {
                        val imageName =
                            it.split("/").last().split(".").first()  // Extract name (A, B, C, etc.)
                        DictionaryPicture(it, imageName, "No definition")
                    }))
                } else {
                    emit(Result.Error("No pictures found in API"))
                }
            } catch (e: Exception) {
                emit(Result.Error(message = e.message ?: "Unknown Error"))
            }
        }
    }


    /**
     * Mengunduh gambar dari API dan menyimpannya ke dalam database.
     */
    suspend fun downloadImage(
        imageUrl: String
    ): Flow<Result<Boolean>> = flow {
        emit(Result.Loading)

        try {
            val existingPicture = downloadDictionaryPictureDao.getDownloadedPictureByUrl(imageUrl)
            if (existingPicture != null) {
                emit(Result.Success(true))
            } else {
                val downloadedPicture = DownloadedDictionaryPicture(
                    word = "Unknown",
                    imageUrl = imageUrl,
                    definition = "No definition"
                )
                downloadDictionaryPictureDao.insertDownloadedPicture(downloadedPicture)
                emit(Result.Success(true))
            }
        } catch (e: Exception) {
            emit(Result.Error(message = e.message ?: "Failed to download image"))
        }
    }

    /**
     * Menghapus gambar yang sudah diunduh berdasarkan URL.
     */
    suspend fun deleteImage(imageUrl: String): Flow<Result<Boolean>> = flow {
        emit(Result.Loading)

        try {
            val picture = downloadDictionaryPictureDao.getDownloadedPictureByUrl(imageUrl)
            if (picture != null) {
                downloadDictionaryPictureDao.deleteDownloadedPictureByUrl(imageUrl)
                emit(Result.Success(true))
            } else {
                emit(Result.Error("Picture not found"))
            }
        } catch (e: Exception) {
            emit(Result.Error(message = e.message ?: "Failed to delete image"))
        }
    }
}
