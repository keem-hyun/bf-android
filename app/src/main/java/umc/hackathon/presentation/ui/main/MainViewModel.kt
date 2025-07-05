package umc.hackathon.presentation.ui.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import umc.hackathon.domain.TestRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val testRepository: TestRepository) : ViewModel() {
    init {
        testRepository.foo()
    }
}