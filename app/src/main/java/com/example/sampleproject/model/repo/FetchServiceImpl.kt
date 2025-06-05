package com.example.sampleproject.model.repo

import android.util.Log
import com.example.sampleproject.model.Response
import com.example.sampleproject.model.ServiceResponse
import com.example.sampleproject.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchServiceImpl @Inject constructor(
    private val getApiService: ApiService
) : FetchService {


    override suspend fun getServices(): Flow<Response<ServiceResponse>> = flow {
        emit(Response.Loading())
        try{
            val service = getApiService.getServices()
//            if (service.data.isNotEmpty()) {
                emit(Response.Success(service))
//            } else {
//                emit(Response.Error(" No Response "))
//            }
        }catch (e: Exception){
            emit(Response.Error(e.localizedMessage))
        }
    }
}