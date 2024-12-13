package com.application.isyara.viewmodel.dictionary

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.isyara.data.model.DictionaryPicture
import com.application.isyara.data.repository.DictionaryPictureRepository
import com.application.isyara.utils.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DictionaryPictureViewModel @Inject constructor(
    private val repository: DictionaryPictureRepository
) : ViewModel() {

    private val _state = MutableStateFlow<Result<List<DictionaryPicture>>>(Result.Idle)
    val state: StateFlow<Result<List<DictionaryPicture>>> get() = _state

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> get() = _searchQuery

    private val _downloadState = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    val downloadState: StateFlow<Map<String, Boolean>> get() = _downloadState

    private val _deleteState = MutableStateFlow<Result<Boolean>>(Result.Idle)
    val deleteState: StateFlow<Result<Boolean>> get() = _deleteState

    /**
     * Mengambil gambar yang sudah diunduh (offline) atau gambar dari API jika tidak ada.
     */
    fun fetchDictionaryPictures() {
        viewModelScope.launch {
            _state.value = Result.Loading
            try {
                repository.getDictionaryPictures().collect { result ->
                    _state.value = result
                }
            } catch (e: Exception) {
                _state.value = Result.Error(message = e.message ?: "Error fetching pictures")
            }
        }
    }

    /**
     * Mengunduh gambar dari API dan menyimpannya ke dalam database.
     */
    fun downloadPicture(imageUrl: String) {
        viewModelScope.launch {
            _downloadState.value += (imageUrl to true)
            try {
                repository.downloadImage(imageUrl).collect { result ->
                    if (result is Result.Success) {
                        _downloadState.value += (imageUrl to false)
                    }
                }
            } catch (e: Exception) {
                _downloadState.value = _downloadState.value + (imageUrl to false)
                Log.e("DictionaryPictureVM", "Error downloading image: ${e.message}")
            }
        }
    }

    /**
     * Menghapus gambar yang sudah diunduh berdasarkan URL.
     */
    fun deleteDownloadedPicture(imageUrl: String) {
        viewModelScope.launch {
            _deleteState.value = Result.Loading
            try {
                repository.deleteImage(imageUrl).collect { result ->
                    _deleteState.value = result
                }
            } catch (e: Exception) {
                _deleteState.value = Result.Error(message = e.message ?: "Error deleting picture")
            }
        }
    }

    /**
     * Update search query yang digunakan untuk filter gambar berdasarkan kata kunci.
     */
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

}
