package com.example.sampleproject.retrofit

import com.example.sampleproject.model.ServiceResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("servicesNames")
    suspend fun getServices(): Response<ServiceResponse>
}