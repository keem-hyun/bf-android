package umc.hackathon.presentation.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import umc.hackathon.domain.RegionRepository
import umc.hackathon.domain.TestRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val regionRepository: RegionRepository) :
    ViewModel() {
    init {
    }
}