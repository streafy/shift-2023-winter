package ru.cft.shift2023winter.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.cft.shift2023winter.R
import ru.cft.shift2023winter.domain.entity.Brewery

class BreweryAdapter : PagingDataAdapter<Brewery, BreweryViewHolder>(UserComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreweryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BreweryViewHolder(
            view = inflater.inflate(R.layout.breweries_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BreweryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class BreweryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name get() = itemView.findViewById<TextView>(R.id.name)
    private val city get() = itemView.findViewById<TextView>(R.id.city)
    private val address get() = itemView.findViewById<TextView>(R.id.address)

    fun bind(brewery: Brewery?) {
        name.text = brewery?.name ?: "Brewery"
        city.text = brewery?.city ?: "City"
        address.text = brewery?.street ?: "Address"

    }
}

object UserComparator : DiffUtil.ItemCallback<Brewery>() {
    override fun areItemsTheSame(oldItem: Brewery, newItem: Brewery): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Brewery, newItem: Brewery): Boolean {
        return oldItem == newItem
    }
}