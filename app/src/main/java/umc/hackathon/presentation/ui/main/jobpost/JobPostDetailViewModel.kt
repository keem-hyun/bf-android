package umc.hackathon.presentation.ui.main.jobpost

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import umc.hackathon.domain.JobPostingRepository
import umc.hackathon.model.JobPosting
import javax.inject.Inject

@HiltViewModel
class JobPostDetailViewModel @Inject constructor(
    private val jobPostingRepository: JobPostingRepository
) : ViewModel() {
    
    private val _jobPosting = MutableStateFlow<JobPosting?>(null)
    val jobPosting: StateFlow<JobPosting?> = _jobPosting.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    fun loadJobPosting(jobId: Int) {
        Log.d("JobPostDetailViewModel", "🎯 공고 상세 로딩 시작: jobId=$jobId")
        viewModelScope.launch {
            _isLoading.value = true
            
            try {
                val jobPosting = jobPostingRepository.getDetailJobPosting(jobId)
                _jobPosting.value = jobPosting
                if (jobPosting != null) {
                    Log.d("JobPostDetailViewModel", "✅ 공고 상세 로딩 성공: ${jobPosting.title}")
                } else {
                    Log.w("JobPostDetailViewModel", "⚠️ 공고를 찾을 수 없음: jobId=$jobId")
                }
            } catch (e: Exception) {
                Log.e("JobPostDetailViewModel", "❌ 공고 상세 로딩 실패: ${e.message}", e)
                _jobPosting.value = null
            } finally {
                _isLoading.value = false
                Log.d("JobPostDetailViewModel", "🏁 공고 상세 로딩 완료")
            }
        }
    }
}