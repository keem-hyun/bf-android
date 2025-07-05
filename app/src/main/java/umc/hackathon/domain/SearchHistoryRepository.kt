package umc.hackathon.domain

import umc.hackathon.model.SearchHistory

interface SearchHistoryRepository {
    fun getSearchHistory(): List<SearchHistory>
    fun addSearchHistory(query: String)
    fun removeSearchHistory(query: String)
    fun clearSearchHistory()
}