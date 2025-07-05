package umc.hackathon.data.datasource

import android.util.Log
import umc.hackathon.data.network.JobPostingService
import umc.hackathon.data.network.model.response.toDomainModel
import umc.hackathon.model.JobPosting
import javax.inject.Inject

class RemoteJobPostingDataSource @Inject constructor(
    private val jobPostingService: JobPostingService
) : JobPostingDataSource {
    
    override suspend fun getRecommendJobPostings(): List<JobPosting> {
        Log.d("RemoteJobPostingDataSource", "📱 추천 공고 목록 요청")
        val response = jobPostingService.getRecommendJobPostings()
        return if (response.isSuccess) {
            val jobPostings = response.result.map { it.toDomainModel() }
            Log.d("RemoteJobPostingDataSource", "✅ 도메인 모델 변환 완료: ${jobPostings.size}개 공고")
            jobPostings.forEachIndexed { index, job ->
                Log.d("RemoteJobPostingDataSource", "  ${index + 1}. ${job.title} (${job.company})")
            }
            jobPostings
        } else {
            Log.e("RemoteJobPostingDataSource", "❌ API 응답 실패: ${response.message}")
            throw Exception(response.message)
        }
    }
    
    override suspend fun getDetailJobPosting(id: Int): JobPosting? {
        Log.d("RemoteJobPostingDataSource", "📱 공고 상세 요청: ID=$id")
        return try {
            val response = jobPostingService.getJobPostingDetail(id)
            if (response.isSuccess) {
                val jobPosting = response.result.toDomainModel()
                Log.d("RemoteJobPostingDataSource", "✅ 공고 상세 조회 성공: ${jobPosting.title}")
                jobPosting
            } else {
                Log.w("RemoteJobPostingDataSource", "⚠️ 공고 상세 조회 실패: ${response.message}")
                null
            }
        } catch (e: Exception) {
            Log.e("RemoteJobPostingDataSource", "❌ 공고 상세 조회 에러 (ID=$id): ${e.message}", e)
            null
        }
    }
}