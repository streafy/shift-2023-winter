package ru.cft.shift2023winter.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import ru.cft.shift2023winter.domain.entity.Brewery

object BreweryDiffUtil : DiffUtil.ItemCallback<Brewery>() {
    override fun areItemsTheSame(oldItem: Brewery, newItem: Brewery): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Brewery, newItem: Brewery): Boolean {
        return oldItem == newItem
    }
}