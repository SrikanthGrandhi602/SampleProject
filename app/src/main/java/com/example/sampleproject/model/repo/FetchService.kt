package com.example.sampleproject.model.repo

import com.example.sampleproject.model.Response
import com.example.sampleproject.model.ServiceResponse
import kotlinx.coroutines.flow.Flow

interface FetchService {

    suspend fun getServices() : Flow<Response<ServiceResponse>>
}