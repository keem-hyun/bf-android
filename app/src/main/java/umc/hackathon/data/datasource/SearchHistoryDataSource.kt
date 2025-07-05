package umc.hackathon.data.datasource

import umc.hackathon.model.SearchHistory

interface SearchHistoryDataSource {
    fun getSearchHistory(): List<SearchHistory>
    fun addSearchHistory(query: String)
    fun removeSearchHistory(query: String)
    fun clearSearchHistory()
}