package com.example.sampleproject

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sampleproject.adapter.ServiceAdapter
import com.example.sampleproject.databinding.ActivityMainBinding
import com.example.sampleproject.retrofit.RetrofitClient
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val serviceAdapter = ServiceAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup RecyclerView with a grid layout (2 columns)
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = serviceAdapter

        fetchData()
    }

    private fun fetchData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.apiService.getServices()
                // Log response for debugging
                println("Response code: ${response.code()}, body: ${response.body()}")
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null && body.status == "Success") {
                        val services = body.data
                        withContext(Dispatchers.Main) {
                            serviceAdapter.submitList(services)
                        }
                    } else {
                        showError("Invalid status: ${body?.status}")
                    }
                } else {
                    showError("HTTP Error: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                showError("Exception: ${e.localizedMessage}")
            }
        }
    }

    private suspend fun showError(message: String) {
        withContext(Dispatchers.Main) {
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
        }
    }
}

