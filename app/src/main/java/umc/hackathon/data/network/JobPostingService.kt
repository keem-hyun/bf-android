package umc.hackathon.data.network

import android.util.Log
import umc.hackathon.data.network.model.request.JobApplicationRequest
import umc.hackathon.data.network.model.response.BaseResponse
import umc.hackathon.data.network.model.response.JobPostingResponse
import javax.inject.Inject

class JobPostingService @Inject constructor(
    private val apiClient: APIClient
) {
    
    suspend fun getRecommendJobPostings(): BaseResponse<List<JobPostingResponse>> {
        Log.d("JobPostingService", "🚀 API 호출 시작: api/job-posts")
        return try {
            val response: BaseResponse<List<JobPostingResponse>> = apiClient.get("api/job-posts")
            Log.d("JobPostingService", "✅ API 응답 성공: isSuccess=${response.isSuccess}, message=${response.message}")
            Log.d("JobPostingService", "📊 공고 개수: ${response.result.size}")
            response
        } catch (e: Exception) {
            Log.e("JobPostingService", "❌ API 호출 실패: ${e.message}", e)
            throw e
        }
    }
    
    suspend fun getJobPostingDetail(jobPostId: Int): BaseResponse<JobPostingResponse> {
        Log.d("JobPostingService", "🚀 API 호출 시작: api/job-posts/$jobPostId")
        return try {
            val response: BaseResponse<JobPostingResponse> = apiClient.get("api/job-posts/$jobPostId")
            Log.d("JobPostingService", "✅ API 응답 성공: isSuccess=${response.isSuccess}, message=${response.message}")
            Log.d("JobPostingService", "📄 공고 제목: ${response.result.title}")
            response
        } catch (e: Exception) {
            Log.e("JobPostingService", "❌ API 호출 실패 (jobPostId=$jobPostId): ${e.message}", e)
            throw e
        }
    }
    
    suspend fun submitJobApplication(jobPostId: Int, applicantId: Int, applications: List<JobApplicationRequest>): BaseResponse<Any> {
        Log.d("JobPostingService", "🚀 API 호출 시작: api/applications/$jobPostId/$applicantId")
        return try {
            val response: BaseResponse<Any> = apiClient.post("api/applications/$jobPostId/$applicantId", applications)
            Log.d("JobPostingService", "✅ 지원서 제출 성공: isSuccess=${response.isSuccess}, message=${response.message}")
            response
        } catch (e: Exception) {
            Log.e("JobPostingService", "❌ 지원서 제출 실패 (jobPostId=$jobPostId, applicantId=$applicantId): ${e.message}", e)
            throw e
        }
    }
}