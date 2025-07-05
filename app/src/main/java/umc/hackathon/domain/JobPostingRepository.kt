package umc.hackathon.domain

import umc.hackathon.model.JobPosting

interface JobPostingRepository {
    suspend fun getRecommendJobPostings(): List<JobPosting>
    suspend fun getDetailJobPosting(id: Int): JobPosting?
}