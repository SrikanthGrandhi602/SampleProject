package com.example.sampleproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sampleproject.databinding.ItemServicesBinding
import com.example.sampleproject.model.ServiceItem

class ServiceAdapter : RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {

    private var services = listOf<ServiceItem>()

    fun submitList(list: List<ServiceItem>) {
        // Optional log to check list size:
        println("Adapter received list size: ${list.size}")
        services = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val binding = ItemServicesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ServiceViewHolder(binding)
    }

    override fun getItemCount() = services.size

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        holder.bind(services[position])
    }

    inner class ServiceViewHolder(private val binding: ItemServicesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(serviceItem: ServiceItem) {
            binding.serviceName.text = serviceItem.service
            Glide.with(binding.root)
                .load(serviceItem.image)
                .into(binding.serviceIcon)
        }
    }
}