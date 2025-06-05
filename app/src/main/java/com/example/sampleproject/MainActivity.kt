package com.example.sampleproject

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sampleproject.adapter.ServiceAdapter
import com.example.sampleproject.databinding.ActivityMainBinding
import com.example.sampleproject.model.viewmodel.ServiceViewModel
import com.example.sampleproject.retrofit.RetrofitClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val serviceAdapter = ServiceAdapter()
    private val viewmodel : ServiceViewModel by viewModels<ServiceViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup RecyclerView with a grid layout (2 columns)
        fetchData()
    }

    private fun fetchData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                viewmodel.getService()
                viewmodel._stateValue.collectLatest {
                    withContext(Dispatchers.Main) {
                        binding.recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 2)
                        binding.recyclerView.adapter = serviceAdapter
                        serviceAdapter.submitList(it.data)
                    }
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


