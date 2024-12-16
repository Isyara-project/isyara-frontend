package com.application.isyara.data.repository

import com.application.isyara.data.di.RetrofitMain
import com.application.isyara.data.local.DownloadedDictionaryPicture
import com.application.isyara.data.local.DownloadDictionaryPictureDao
import com.application.isyara.data.model.DictionaryPictureItem
import com.application.isyara.data.remote.ApiService
import com.application.isyara.utils.dictionary.NetworkHelper
import com.application.isyara.utils.dictionary.capitalizeWords
import com.application.isyara.utils.state.Result
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
import timber.log.Timber

@Singleton
class DictionaryPictureRepository @Inject constructor(
    @RetrofitMain private val apiService: ApiService,
    private val downloadDictionaryPictureDao: DownloadDictionaryPictureDao,
    private val networkHelper: NetworkHelper
) {

    private val _apiPictures = MutableStateFlow<List<String>>(emptyList())
    val apiPictures: StateFlow<List<String>> = _apiPictures.asStateFlow()

    init {
        if (networkHelper.isNetworkConnected()) {
            fetchApiPictures()
        }
    }

    /**
     * Fungsi untuk mengambil gambar dari API dan mengupdate _apiPictures
     */
    private fun fetchApiPictures() {
        kotlinx.coroutines.GlobalScope.launch {
            try {
                val apiPics = apiService.getDictionaryPicture()
                _apiPictures.value = apiPics
            } catch (e: Exception) {
                Timber.e(e, "Error fetching API pictures: ${e.localizedMessage}")
            }
        }
    }

    /**
     * Mengambil daftar semua gambar dengan status unduhan secara reaktif.
     * Menggabungkan data dari API dengan status unduhan dari database lokal.
     */
    fun getDictionaryPictures(): Flow<Result<List<DictionaryPictureItem>>> =
        combine(
            apiPictures,
            downloadDictionaryPictureDao.getAllDownloadedPictures()
        ) { apiPics, downloadedPics ->
            if (networkHelper.isNetworkConnected()) {
                if (apiPics.isNotEmpty()) {
                    val downloadedUrls = downloadedPics.map { it.url }.toSet()
                    val pictureItems = apiPics.map { url ->
                        DictionaryPictureItem(
                            url = url,
                            name = url.substringAfterLast("/").substringBeforeLast(".")
                                .capitalizeWords(),
                            isDownloaded = downloadedUrls.contains(url)
                        )
                    }
                    Result.Success(pictureItems)
                } else {
                    Result.Error("Tidak ada gambar ditemukan di API")
                }
            } else {
                val pictureItems = downloadedPics.map { picture ->
                    DictionaryPictureItem(
                        url = picture.url,
                        name = picture.url.substringAfterLast("/").substringBeforeLast(".")
                            .capitalizeWords(),
                        isDownloaded = true
                    )
                }
                Result.Success(pictureItems)
            }
        }
            .onStart { emit(Result.Loading) }
            .catch { e ->
                emit(Result.Error("Gagal mengambil data gambar"))
            }

    /**
     * Mengunduh gambar dari API dan menyimpannya ke dalam database.
     */
    fun downloadImage(imageUrl: String): Flow<Result<Boolean>> = flow {
        emit(Result.Loading)

        try {
            val existingPicture = downloadDictionaryPictureDao.getDownloadedPictureByUrl(imageUrl)
            if (existingPicture != null) {
                emit(Result.Success(true))
            } else {
                val downloadedPicture = DownloadedDictionaryPicture(
                    url = imageUrl,
                    localPath = "",
                    timestamp = System.currentTimeMillis()
                )
                downloadDictionaryPictureDao.insertAll(listOf(downloadedPicture))
                emit(Result.Success(true))
            }
        } catch (e: Exception) {
            Timber.e(e, "Failed to download image: ${e.localizedMessage}")
            emit(Result.Error(message = e.message ?: "Gagal mengunduh gambar"))
        }
    }

    /**
     * Menghapus gambar yang sudah diunduh berdasarkan URL.
     */
    fun deleteImage(imageUrl: String): Flow<Result<Boolean>> = flow {
        emit(Result.Loading)

        try {
            val picture = downloadDictionaryPictureDao.getDownloadedPictureByUrl(imageUrl)
            if (picture != null) {
                downloadDictionaryPictureDao.deleteDownloadedPictureByUrl(imageUrl)
                emit(Result.Success(true))
            } else {
                emit(Result.Error("Gambar tidak ditemukan"))
            }
        } catch (e: Exception) {
            Timber.e(e, "Failed to delete image: ${e.localizedMessage}")
            emit(Result.Error(message = e.message ?: "Gagal menghapus gambar"))
        }
    }

    fun getAllDownloadedPictures(): Flow<List<DownloadedDictionaryPicture>> {
        return downloadDictionaryPictureDao.getAllDownloadedPictures()
    }

    suspend fun deleteAllDownloadedPictures() {
        downloadDictionaryPictureDao.deleteAllPictures()
    }
}
