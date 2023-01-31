package ru.cft.shift2023winter.presentation.screens

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.cft.shift2023winter.R
import ru.cft.shift2023winter.domain.entity.Brewery

class BreweriesAdapter(
    private val breweryClickListener: (Brewery) -> Unit
) : RecyclerView.Adapter<BreweryViewHolder>() {

    var breweries: List<Brewery> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreweryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.breweries_list_item, parent, false)
        return BreweryViewHolder(view)
    }

    override fun getItemCount(): Int =
        breweries.size

    override fun onBindViewHolder(holder: BreweryViewHolder, position: Int) {
        holder.bind(breweries[position], breweryClickListener)
    }

}

class BreweryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name get() = itemView.findViewById<TextView>(R.id.name)
    private val city get() = itemView.findViewById<TextView>(R.id.city)
    private val address get() = itemView.findViewById<TextView>(R.id.address)

    fun bind(brewery: Brewery, breweryClickListener: (Brewery) -> Unit) {
        name.text = brewery.name
        city.text = brewery.city
        address.text = brewery.street

        itemView.setOnClickListener {
            breweryClickListener(brewery)
        }
    }
}