package com.example.artspace.data

data class MasterpieceUiState(
    val currentIndex: Int = 0,
    val masterpieces: List<Masterpiece> = emptyList()
)
