package umc.hackathon.presentation.ui.main.search

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import umc.hackathon.domain.SearchHistoryRepository
import umc.hackathon.model.SearchHistory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository
) : ViewModel() {
    
    var searchText by mutableStateOf("")
        private set
    
    var searchHistory by mutableStateOf<List<SearchHistory>>(emptyList())
        private set
    
    init {
        loadSearchHistory()
    }
    
    fun updateSearchText(text: String) {
        searchText = text
    }
    
    fun addSearchHistory(query: String) {
        if (query.trim().isNotEmpty()) {
            searchHistoryRepository.addSearchHistory(query.trim())
            loadSearchHistory()
            searchText = "" // 검색 후 텍스트 초기화
        }
    }
    
    fun removeSearchHistory(query: String) {
        searchHistoryRepository.removeSearchHistory(query)
        loadSearchHistory()
    }
    
    fun clearSearchHistory() {
        searchHistoryRepository.clearSearchHistory()
        loadSearchHistory()
    }
    
    fun selectSearchHistory(query: String) {
        searchText = query
    }
    
    private fun loadSearchHistory() {
        searchHistory = searchHistoryRepository.getSearchHistory()
    }
}