package umc.hackathon.presentation.ui.main.jobpost

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
        viewModelScope.launch {
            _isLoading.value = true
            
            try {
                val jobPosting = jobPostingRepository.getDetailJobPosting(jobId)
                _jobPosting.value = jobPosting
            } catch (e: Exception) {
                // 에러 처리
                _jobPosting.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }
}