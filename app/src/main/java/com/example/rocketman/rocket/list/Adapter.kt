package com.example.rocketman.rocket.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.rocketman.databinding.ItemRocketBinding
import com.example.rocketman.rocket.Rocket

class Adapter(
    private val context: Context
): ListAdapter<Rocket, Holder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            context,
            ItemRocketBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}

private class DiffCallback: DiffUtil.ItemCallback<Rocket>() {
    override fun areItemsTheSame(oldItem: Rocket, newItem: Rocket): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Rocket, newItem: Rocket): Boolean {
        return oldItem == newItem
    }
}