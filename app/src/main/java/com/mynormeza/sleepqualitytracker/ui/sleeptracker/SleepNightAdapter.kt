package com.mynormeza.sleepqualitytracker.ui.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mynormeza.sleepqualitytracker.R
import com.mynormeza.sleepqualitytracker.convertDurationToFormatted
import com.mynormeza.sleepqualitytracker.convertNumericQualityToString
import com.mynormeza.sleepqualitytracker.databinding.ItemSleepNightBinding
import com.mynormeza.sleepqualitytracker.db.SleepNight

class SleepNightAdapter : ListAdapter<SleepNight, SleepNightAdapter.ViewHolder>(SleepNightDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.from(parent)

    class ViewHolder private constructor(val binding: ItemSleepNightBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater =LayoutInflater.from(parent.context)
                val binding = ItemSleepNightBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }

        fun bind(item: SleepNight) {
            binding.sleep = item
            binding.executePendingBindings()
        }
    }

    class SleepNightDiffCallback : DiffUtil.ItemCallback<SleepNight>() {
        override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
            return oldItem.nightId == newItem.nightId
        }

        override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
            return oldItem == newItem
        }

    }
}