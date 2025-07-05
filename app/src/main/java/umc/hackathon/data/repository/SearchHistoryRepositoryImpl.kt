package umc.hackathon.data.repository

import umc.hackathon.data.datasource.SearchHistoryDataSource
import umc.hackathon.domain.SearchHistoryRepository
import umc.hackathon.model.SearchHistory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchHistoryRepositoryImpl @Inject constructor(
    private val localDataSource: SearchHistoryDataSource
) : SearchHistoryRepository {
    
    override fun getSearchHistory(): List<SearchHistory> {
        return localDataSource.getSearchHistory()
    }
    
    override fun addSearchHistory(query: String) {
        localDataSource.addSearchHistory(query)
    }
    
    override fun removeSearchHistory(query: String) {
        localDataSource.removeSearchHistory(query)
    }
    
    override fun clearSearchHistory() {
        localDataSource.clearSearchHistory()
    }
}