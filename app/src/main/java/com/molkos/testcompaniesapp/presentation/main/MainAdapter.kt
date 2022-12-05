package com.molkos.testcompaniesapp.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.molkos.testcompaniesapp.databinding.ItemCompanyBinding
import com.molkos.testcompaniesapp.domain.model.Company
import com.molkos.testcompaniesapp.utils.IMAGES_HOST

class MainAdapter(
    private val glide: RequestManager,
    private val onItemClick: (id: Int) -> Unit,
) : RecyclerView.Adapter<MainAdapter.CompanyViewHolder>() {

    private var items = listOf<Company>()

    fun setItems(newItems: List<Company>) {
        items = newItems
        notifyItemRangeChanged(0, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val binding = ItemCompanyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CompanyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class CompanyViewHolder(binding: ItemCompanyBinding) : ViewHolder(binding.root) {

        private val image: ImageView
        private val name: TextView
        private val view: View

        init {
            image = binding.image
            name = binding.name
            view = binding.root
        }

        fun bind(item: Company) {
            name.text = item.name
            glide
                .load("$IMAGES_HOST${item.imageUrl}")
                .override(400, 400)
                .apply(RequestOptions.centerCropTransform())
                .into(image)
            view.setOnClickListener { onItemClick.invoke(item.id) }
        }
    }
}