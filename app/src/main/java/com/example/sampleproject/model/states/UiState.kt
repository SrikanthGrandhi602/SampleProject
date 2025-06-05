package com.example.sampleproject.model.states

import com.example.sampleproject.model.ServiceItem

data class UiState(
    val error:String?= null,
    val isLoading: Boolean = false,
    val data  : List<ServiceItem> = listOf()
)
