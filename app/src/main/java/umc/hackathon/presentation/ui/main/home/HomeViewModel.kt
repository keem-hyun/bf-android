package umc.hackathon.presentation.ui.main.home

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import umc.hackathon.domain.JobPostingRepository
import umc.hackathon.model.JobPosting
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val jobPostingRepository: JobPostingRepository
) : ViewModel() {
    
    private val _currentJobIndex = mutableStateOf(0)
    val currentJobIndex: State<Int> = _currentJobIndex
    
    private val _jobPostings = mutableStateOf<List<JobPosting>>(emptyList())
    val jobPostings: State<List<JobPosting>> = _jobPostings
    
    init {
        loadJobPostings()
    }
    
    private fun loadJobPostings() {
        viewModelScope.launch {
            _jobPostings.value = jobPostingRepository.getRecommendJobPostings()
        }
    }
    
    val currentJob: JobPosting?
        get() = _jobPostings.value.getOrNull(_currentJobIndex.value)
    
    fun nextJob() {
        if (_currentJobIndex.value < _jobPostings.value.size - 1) {
            _currentJobIndex.value += 1
        }
    }
    
    fun previousJob() {
        if (_currentJobIndex.value > 0) {
            _currentJobIndex.value -= 1
        }
    }
    
}