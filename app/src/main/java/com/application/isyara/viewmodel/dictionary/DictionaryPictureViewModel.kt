package com.application.isyara.viewmodel.dictionary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.isyara.data.model.DictionaryPictureItem
import com.application.isyara.data.repository.DictionaryPictureRepository
import com.application.isyara.utils.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DictionaryPictureViewModel @Inject constructor(
    private val repository: DictionaryPictureRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _downloadState = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    val downloadState: StateFlow<Map<String, Boolean>> = _downloadState.asStateFlow()

    val dictionaryPictures: StateFlow<Result<List<DictionaryPictureItem>>> = combine(
        _searchQuery.debounce(300).distinctUntilChanged(),
        repository.getDictionaryPictures()
    ) { query, result ->
        when (result) {
            is Result.Success -> {
                val filteredList = if (query.isEmpty()) {
                    result.data
                } else {
                    // Filter berdasarkan name
                    result.data.filter {
                        it.name.contains(query, ignoreCase = true)
                    }
                }
                Result.Success(filteredList)
            }
            else -> result
        }
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            Result.Idle
        )

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun downloadPicture(imageUrl: String) {
        viewModelScope.launch {
            _downloadState.value = _downloadState.value.toMutableMap().apply { put(imageUrl, true) }

            repository.downloadImage(imageUrl)
                .catch { e ->
                    _downloadState.value =
                        _downloadState.value.toMutableMap().apply { put(imageUrl, false) }
                }
                .collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _downloadState.value =
                                _downloadState.value.toMutableMap().apply { put(imageUrl, false) }
                        }

                        is Result.Error -> {
                            _downloadState.value =
                                _downloadState.value.toMutableMap().apply { put(imageUrl, false) }
                        }

                        is Result.Loading -> {

                        }

                        is Result.Idle -> {

                        }
                    }
                }
        }
    }


    fun deleteDownloadedPicture(imageUrl: String) {
        viewModelScope.launch {
            _downloadState.value = _downloadState.value.toMutableMap().apply { put(imageUrl, true) }

            repository.deleteImage(imageUrl)
                .catch { e ->
                    _downloadState.value =
                        _downloadState.value.toMutableMap().apply { put(imageUrl, false) }
                }
                .collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _downloadState.value =
                                _downloadState.value.toMutableMap().apply { put(imageUrl, false) }

                        }

                        is Result.Error -> {

                            _downloadState.value =
                                _downloadState.value.toMutableMap().apply { put(imageUrl, false) }

                        }

                        is Result.Loading -> {

                        }

                        is Result.Idle -> {

                        }
                    }
                }
        }
    }
}
