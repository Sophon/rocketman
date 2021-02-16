package com.example.rocketman.event

import androidx.recyclerview.widget.RecyclerView
import com.example.rocketman.databinding.ItemEventBinding

class Holder(
    private val binding: ItemEventBinding
): RecyclerView.ViewHolder(binding.root) {

    private lateinit var event: Event

    fun bind(event: Event) {
        this.event = event

        binding.apply {
            txtTitle.text = event.title
            txtDate.text = event.eventDateUtc
            txtDetails.text = event.details
            txtLink.text = event.links?.article
        }
    }
}