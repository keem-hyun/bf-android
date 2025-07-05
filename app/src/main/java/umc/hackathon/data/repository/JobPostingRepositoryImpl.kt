package umc.hackathon.data.repository

import umc.hackathon.data.datasource.JobPostingDataSource
import umc.hackathon.domain.JobPostingRepository
import umc.hackathon.model.JobPosting
import javax.inject.Inject

class JobPostingRepositoryImpl @Inject constructor(
    private val dataSource: JobPostingDataSource
) : JobPostingRepository {
    
    override suspend fun getRecommendJobPostings(): List<JobPosting> {
        return dataSource.getRecommendJobPostings()
    }
    
    override suspend fun getDetailJobPosting(id: Int): JobPosting? {
        return dataSource.getDetailJobPosting(id)
    }
}