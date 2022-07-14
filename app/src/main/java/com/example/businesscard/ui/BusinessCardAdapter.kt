package com.example.businesscard.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.businesscard.R
import com.example.businesscard.data.BusinessCard
import com.example.businesscard.databinding.ItemBusinessCardBinding

class BusinessCardAdapter : ListAdapter<BusinessCard, BusinessCardAdapter.ViewHolder>(BusinessCallCaback()) {

    var listenerShare : (View) -> Unit = {}
    private lateinit var binding: ItemBusinessCardBinding

    inner class ViewHolder(private val binding: ItemBusinessCardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(item : BusinessCard){
            with(binding){
                with(item){
                    tvName.text = name
                    tvPhone.text = phone
                    tvEmail.text = email
                    tvBusinessName.text = business
                    if(backgroundColor == "#e0e0e0" || backgroundColor == "#fff176"){
                        setTextColorBlack()
                    }
                    root.setCardBackgroundColor(Color.parseColor(backgroundColor))
                    root.setOnClickListener { view ->
                        listenerShare(view)
                    }
                }
            }
        }

        private fun setTextColorBlack() {
            with(binding) {
                tvName.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))
                tvPhone.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))
                tvEmail.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))
                tvBusinessName.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemBusinessCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val businessCard = getItem(position)
        holder.onBind(businessCard)
    }
}

class BusinessCallCaback : DiffUtil.ItemCallback<BusinessCard>(){
    override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard): Boolean {
        return oldItem == newItem
    }
}
