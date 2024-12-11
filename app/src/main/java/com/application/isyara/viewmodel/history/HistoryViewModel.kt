package com.application.isyara.viewmodel.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.isyara.data.repository.DictionaryRepository
import com.application.isyara.data.repository.TranslatedTextRepository
import com.application.isyara.utils.history.HistoryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val translatedTextRepository: TranslatedTextRepository,
    private val dictionaryRepository: DictionaryRepository
) : ViewModel() {

    val historyItems: StateFlow<List<HistoryItem>> = combine(
        translatedTextRepository.getAllTranslatedTexts(),
        dictionaryRepository.getAllDownloadedItems()
    ) { translations, dictionaries ->
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
        (translationItems + dictionaryItems).sortedByDescending { it.timestamp }
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

    fun deleteTranslatedTextById(id: Int) {
        viewModelScope.launch {
            translatedTextRepository.deleteTranslatedTextById(id)
        }
    }

    fun deleteDownloadedDictionaryItemByUrl(url: String) {
        viewModelScope.launch {
            dictionaryRepository.deleteDownloadedItem(url)
        }
    }
}
