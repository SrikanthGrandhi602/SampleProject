package com.example.sampleproject.model

sealed class Response<T>(val data:T?=null, val message:String?=null) {

    class Loading<T> : Response<T>()

    class Success<T>(data: T) : Response<T>(data)

    class Error<T>(message: String) : Response<T>(message=message)
}