package umc.hackathon.data.datasource

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import umc.hackathon.model.SearchHistory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalSearchHistoryDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) : SearchHistoryDataSource {
    
    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("search_history", Context.MODE_PRIVATE)
    }
    
    companion object {
        private const val KEY_SEARCH_HISTORY = "search_history"
        private const val DELIMITER = "||"
        private const val MAX_HISTORY_SIZE = 10
    }
    
    override fun getSearchHistory(): List<SearchHistory> {
        val historyString = sharedPreferences.getString(KEY_SEARCH_HISTORY, "") ?: ""
        if (historyString.isEmpty()) return emptyList()
        
        return historyString.split(DELIMITER)
            .filter { it.isNotEmpty() }
            .map { SearchHistory(it) }
    }
    
    override fun addSearchHistory(query: String) {
        if (query.trim().isEmpty()) return
        
        val currentHistory = getSearchHistory().toMutableList()
        
        // 기존에 같은 검색어가 있다면 제거
        currentHistory.removeAll { it.query == query }
        
        // 맨 앞에 새 검색어 추가
        currentHistory.add(0, SearchHistory(query))
        
        // 최대 개수 제한
        if (currentHistory.size > MAX_HISTORY_SIZE) {
            currentHistory.removeAt(currentHistory.size - 1)
        }
        
        saveHistory(currentHistory)
    }
    
    override fun removeSearchHistory(query: String) {
        val currentHistory = getSearchHistory().toMutableList()
        currentHistory.removeAll { it.query == query }
        saveHistory(currentHistory)
    }
    
    override fun clearSearchHistory() {
        sharedPreferences.edit().remove(KEY_SEARCH_HISTORY).apply()
    }
    
    private fun saveHistory(history: List<SearchHistory>) {
        val historyString = history.joinToString(DELIMITER) { it.query }
        sharedPreferences.edit().putString(KEY_SEARCH_HISTORY, historyString).apply()
    }
}