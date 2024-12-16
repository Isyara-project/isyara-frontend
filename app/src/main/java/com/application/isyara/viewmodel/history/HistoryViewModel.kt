package com.application.isyara.viewmodel.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.isyara.data.repository.DictionaryPictureRepository
import com.application.isyara.data.repository.DictionaryRepository
import com.application.isyara.data.repository.TranslatedTextRepository
import com.application.isyara.utils.history.HistoryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val translatedTextRepository: TranslatedTextRepository,
    private val dictionaryRepository: DictionaryRepository,
    private val dictionaryPictureRepository: DictionaryPictureRepository
) : ViewModel() {

    val historyItems: StateFlow<List<HistoryItem>> = combine(
        translatedTextRepository.getAllTranslatedTexts(),
        dictionaryRepository.getAllDownloadedItems(),
        dictionaryPictureRepository.getAllDownloadedPictures()
    ) { translations, dictionaries, pictures ->
        val translationItems = translations.map {
            HistoryItem.Translation(
                id = it.id,
                text = it.text,
                timestamp = it.timestamp
            )
        }
        val dictionaryItems = dictionaries.map {
            HistoryItem.DownloadedDictionary(
                url = it.url,
                title = it.title,
                localPath = it.localPath,
                imageUrl = it.imageUrl,
                localImagePath = it.localImagePath,
                timestamp = it.timestamp
            )
        }

        val pictureItems = pictures.map {
            HistoryItem.DownloadedDictionaryPicture(
                id = it.id,
                url = it.url,
                localPath = it.localPath,
                timestamp = it.timestamp
            )
        }
        (translationItems + dictionaryItems + pictureItems).sortedByDescending { it.timestamp }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun deleteAllTranslatedTexts() {
        viewModelScope.launch {
            translatedTextRepository.deleteAllTranslatedTexts()
        }
    }

    fun deleteAllDownloadedDictionaries() {
        viewModelScope.launch {
            dictionaryRepository.deleteAllDownloadedItems()
        }
    }

    fun deleteAllDownloadedPictures() {
        viewModelScope.launch {
            dictionaryPictureRepository.deleteAllDownloadedPictures()
        }
    }

    private fun deleteTranslatedTextById(id: Int) {
        viewModelScope.launch {
            translatedTextRepository.deleteTranslatedTextById(id)
        }
    }

    private fun deleteDownloadedDictionaryItemByUrl(url: String) {
        viewModelScope.launch {
            dictionaryRepository.deleteDownloadedItem(url)
        }
    }

    private fun deleteDownloadedPictureByUrl(url: String) {
        viewModelScope.launch {
            dictionaryPictureRepository.deleteImage(url)
        }
    }

    fun deleteHistoryItem(item: HistoryItem) {
        viewModelScope.launch {
            when (item) {
                is HistoryItem.Translation -> deleteTranslatedTextById(item.id)
                is HistoryItem.DownloadedDictionary -> deleteDownloadedDictionaryItemByUrl(item.url)
                is HistoryItem.DownloadedDictionaryPicture -> deleteDownloadedPictureByUrl(item.url)
            }
        }
    }
}
