package umc.hackathon.presentation.ui.main.apply

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import umc.hackathon.data.network.JobPostingService
import umc.hackathon.data.network.model.request.JobApplicationRequest
import javax.inject.Inject

@HiltViewModel
class JobApplyViewModel @Inject constructor(
    private val jobPostingService: JobPostingService
) : ViewModel() {
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _isSubmitSuccess = MutableStateFlow(false)
    val isSubmitSuccess: StateFlow<Boolean> = _isSubmitSuccess.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    fun submitApplication(
        jobPostId: Int,
        applicantId: Int,
        applyReason: String,
        message: String
    ) {
        Log.d("JobApplyViewModel", "🎯 지원서 제출 시작: jobPostId=$jobPostId, applicantId=$applicantId")
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            _isSubmitSuccess.value = false
            
            try {
                val applications = listOf(
                    JobApplicationRequest(
                        jobPostQuestionId = 1,
                        answer = applyReason,
                        num = 1
                    ),
                    JobApplicationRequest(
                        jobPostQuestionId = 2,
                        answer = message,
                        num = 2
                    )
                )
                
                val response = jobPostingService.submitJobApplication(jobPostId, applicantId, applications)
                
                if (response.isSuccess) {
                    _isSubmitSuccess.value = true
                    Log.d("JobApplyViewModel", "✅ 지원서 제출 성공")
                } else {
                    _errorMessage.value = response.message
                    Log.w("JobApplyViewModel", "⚠️ 지원서 제출 실패: ${response.message}")
                }
            } catch (e: Exception) {
                _errorMessage.value = "지원서 제출 중 오류가 발생했습니다: ${e.message}"
                Log.e("JobApplyViewModel", "❌ 지원서 제출 실패: ${e.message}", e)
            } finally {
                _isLoading.value = false
                Log.d("JobApplyViewModel", "🏁 지원서 제출 완료")
            }
        }
    }
    
    fun clearError() {
        _errorMessage.value = null
    }
    
    fun resetSubmitState() {
        _isSubmitSuccess.value = false
    }
}