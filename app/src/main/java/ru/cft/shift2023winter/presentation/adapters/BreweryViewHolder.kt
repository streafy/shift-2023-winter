package ru.cft.shift2023winter.presentation.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.cft.shift2023winter.R
import ru.cft.shift2023winter.domain.entity.Brewery

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