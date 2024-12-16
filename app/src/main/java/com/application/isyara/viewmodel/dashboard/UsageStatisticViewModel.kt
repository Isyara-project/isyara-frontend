package com.application.isyara.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.isyara.data.repository.DictionaryPictureRepository
import com.application.isyara.data.repository.DictionaryRepository
import com.application.isyara.data.repository.TranslatedTextRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsageStatisticViewModel @Inject constructor(
    private val translatedTextRepository: TranslatedTextRepository,
    private val dictionaryRepository: DictionaryRepository,
    private val dictionaryPictureRepository: DictionaryPictureRepository
) : ViewModel() {

    private val _totalCharacters = MutableStateFlow(0)
    val totalCharacters: StateFlow<Int> = _totalCharacters

    private val _totalDownloadedDictionaries = MutableStateFlow(0)
    val totalDownloadedDictionaries: StateFlow<Int> = _totalDownloadedDictionaries

    private val _totalDownloadedPictureDictionaries = MutableStateFlow(0)
    val totalDownloadedPictureDictionaries: StateFlow<Int> = _totalDownloadedPictureDictionaries

    init {
        viewModelScope.launch {
            translatedTextRepository.getAllTranslatedTexts().collect { translatedTexts ->
                val total = translatedTexts.sumOf { it.text.length }
                _totalCharacters.value = total
            }
        }

        viewModelScope.launch {
            dictionaryRepository.getAllDownloadedItems().collect { downloadedItems ->
                _totalDownloadedDictionaries.value = downloadedItems.size
            }
        }

        viewModelScope.launch {
            dictionaryPictureRepository.getAllDownloadedPictures()
                .collect { downloadedPictureItems ->
                    _totalDownloadedPictureDictionaries.value = downloadedPictureItems.size
                }
        }
    }
}
