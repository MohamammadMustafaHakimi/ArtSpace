package com.example.artspace.view

import com.example.artspace.data.MasterpieceUiState
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.lifecycle.ViewModel
import com.example.artspace.data.masterpieces
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class MasterpieceViewModel: ViewModel {
    private val _uiState = MutableStateFlow(
        MasterpieceUiState(masterpieces = masterpieces)
    )
    val uiState: StateFlow<MasterpieceUiState> = _uiState.asStateFlow()

    val lastIndex = _uiState.value.masterpieces.size -1 // variable for storing the last index of the masterpiece
    val currentIndex = _uiState.value.currentIndex

    fun nextMasterpiece() {
        _uiState.value = _uiState.value.copy(
            currentIndex = if (currentIndex == lastIndex) 0 else currentIndex + 1
        )
    }

    fun previousMasterpiece() {
        _uiState.value = _uiState.value.copy(
            currentIndex = if (currentIndex == 0) lastIndex else currentIndex - 1
        )
    }
}