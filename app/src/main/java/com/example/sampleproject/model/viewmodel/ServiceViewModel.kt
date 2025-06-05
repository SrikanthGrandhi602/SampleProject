package com.example.sampleproject.model.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleproject.model.Response
import com.example.sampleproject.model.repo.FetchService
import com.example.sampleproject.model.repo.FetchServiceImpl
import com.example.sampleproject.model.states.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServiceViewModel @Inject constructor(
    private val fetchService: FetchService
) : ViewModel(){

    val stateValue = MutableStateFlow(UiState())
    var _stateValue = stateValue.asStateFlow()
    fun getService(){
        viewModelScope.launch {
            fetchService.getServices().collectLatest { response ->
                val updateState = when (response){
                    is Response.Loading -> UiState(isLoading = true)
                    is Response.Error -> UiState(isLoading = false, error = response.message)
                    is Response.Success -> UiState(data = response.data!!.data)
                }
                stateValue.update { updateState }
            }
        }
    }
}