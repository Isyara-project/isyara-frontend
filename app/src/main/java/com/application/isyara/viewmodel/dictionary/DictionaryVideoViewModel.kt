package com.application.isyara.viewmodel.dictionary

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.isyara.data.repository.DictionaryRepository
import com.application.isyara.utils.dictionary.VideoItems
import com.application.isyara.utils.dictionary.capitalizeWords
import com.application.isyara.utils.dictionary.extractThumbnail
import com.application.isyara.utils.dictionary.extractThumbnailFromUrl
import com.application.isyara.utils.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@FlowPreview
@HiltViewModel
class DictionaryVideoViewModel @Inject constructor(
    private val dictionaryRepository: DictionaryRepository
) : ViewModel() {

    private val _downloadStatus = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    val downloadStatus: StateFlow<Map<String, Boolean>> = _downloadStatus.asStateFlow()

    private val _searchQuery = MutableStateFlow("")

    private val _thumbnailCache = MutableStateFlow<Map<String, ImageBitmap?>>(emptyMap())
    val thumbnailCache: StateFlow<Map<String, ImageBitmap?>> = _thumbnailCache.asStateFlow()

    @Suppress("UNCHECKED_CAST")
    internal val combinedVideos: StateFlow<Result<List<VideoItems>>> = combine(
        _searchQuery.debounce(300).distinctUntilChanged(),
        dictionaryRepository.getVideoList(),
        dictionaryRepository.getAllDownloadedItems()
    ) { query, onlineResult, downloadedList ->
        if (query.isEmpty()) {
            when (onlineResult) {
                is Result.Success -> {
                    val downloadedUrls = downloadedList.map { it.url }
                    val videoItems = downloadedList.map { downloaded ->
                        VideoItems(
                            url = downloaded.url,
                            title = downloaded.title,
                            isDownloaded = true,
                            localPath = downloaded.localPath
                        )
                    } + onlineResult.data.filter { !downloadedUrls.contains(it) }.map { url ->
                        VideoItems(
                            url = url,
                            title = extractTitleFromUrl(url),
                            isDownloaded = false,
                            localPath = null
                        )
                    }
                    Result.Success(videoItems)
                }

                is Result.Error -> {
                    if (downloadedList.isNotEmpty()) {
                        val videoItems = downloadedList.map { downloaded ->
                            VideoItems(
                                url = downloaded.url,
                                title = downloaded.title,
                                isDownloaded = true,
                                localPath = downloaded.localPath
                            )
                        }
                        Result.Success(videoItems)
                    } else {
                        onlineResult
                    }
                }

                else -> onlineResult
            }
        } else {
            when (onlineResult) {
                is Result.Success -> {
                    val filteredOnline =
                        onlineResult.data.filter { it.contains(query, ignoreCase = true) }
                    val filteredDownloaded =
                        downloadedList.filter { it.title.contains(query, ignoreCase = true) }
                    val videoItems = filteredDownloaded.map { downloaded ->
                        VideoItems(
                            url = downloaded.url,
                            title = downloaded.title,
                            isDownloaded = true,
                            localPath = downloaded.localPath
                        )
                    } + filteredOnline.filter { !downloadedList.any { dl -> dl.url == it } }
                        .map { url ->
                            VideoItems(
                                url = url,
                                title = extractTitleFromUrl(url),
                                isDownloaded = false,
                                localPath = null
                            )
                        }
                    Result.Success(videoItems)
                }

                is Result.Error -> {
                    val filteredDownloaded =
                        downloadedList.filter { it.title.contains(query, ignoreCase = true) }
                    if (filteredDownloaded.isNotEmpty()) {
                        val videoItems = filteredDownloaded.map { downloaded ->
                            VideoItems(
                                url = downloaded.url,
                                title = downloaded.title,
                                isDownloaded = true,
                                localPath = downloaded.localPath
                            )
                        }
                        Result.Success(videoItems)
                    } else {
                        Result.Error("Tidak ada video ditemukan.")
                    }
                }

                else -> onlineResult
            }
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        Result.Idle
    ) as StateFlow<Result<List<VideoItems>>>

    init {
        loadVideos()
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    suspend fun getLocalPath(url: String): String? {
        val path = dictionaryRepository.getLocalPath(url)
        Timber.d("DictionaryViewModel: getLocalPath for $url: $path")
        return path
    }

    private fun loadVideos() {
        viewModelScope.launch {
            combinedVideos.collect {
                // Handle any additional logic if needed
            }
        }
    }

    fun downloadItem(url: String, title: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            _downloadStatus.value = _downloadStatus.value.toMutableMap().apply {
                put(url, true)
            }
            val success = dictionaryRepository.downloadItem(url, title)
            _downloadStatus.value = _downloadStatus.value.toMutableMap().apply {
                put(url, false)
            }
            onResult(success)
        }
    }

    fun deleteDownloadedItem(url: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val success = dictionaryRepository.deleteDownloadedItem(url)
            if (success) {
                _thumbnailCache.value = _thumbnailCache.value.toMutableMap().apply {
                    remove(url)
                }
            }
            onResult(success)
        }
    }

    private fun extractTitleFromUrl(url: String): String {
        return url.substringAfterLast("/").substringBeforeLast(".mp4").replace("-", " ")
            .capitalizeWords()
    }

    fun loadThumbnail(video: VideoItems) {
        if (_thumbnailCache.value.containsKey(video.url)) return

        viewModelScope.launch(Dispatchers.IO) {
            val extractedThumbnail = if (video.isDownloaded && video.localPath != null) {
                extractThumbnail(video.localPath)
            } else {
                extractThumbnailFromUrl(video.url)
            }
            _thumbnailCache.value = _thumbnailCache.value.toMutableMap().apply {
                put(video.url, extractedThumbnail)
            }
            if (extractedThumbnail == null) {
                Timber.e("Failed to extract thumbnail for ${video.url}")
            } else {
                Timber.d("Thumbnail extracted for ${video.url}")
            }
        }
    }

    fun getThumbnail(url: String): ImageBitmap? {
        return _thumbnailCache.value[url]
    }
}
