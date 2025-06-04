package com.example.sampleproject.model


data class ServiceResponse(
    val status: String,
    val message: String,
    val data: List<ServiceItem>
)

data class ServiceItem(
    val id: String,
    val service: String,
    val image: String,
    val position: String,
    val added_on: String,
    val status: String,
    val addedOn: String
)
