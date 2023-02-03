package ru.cft.shift2023winter.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import ru.cft.shift2023winter.R
import ru.cft.shift2023winter.domain.entity.Brewery

class BreweryAdapter(
    private val breweryClickListener: (Brewery) -> Unit
) : PagingDataAdapter<Brewery, BreweryViewHolder>(BreweryDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreweryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BreweryViewHolder(
            view = inflater.inflate(R.layout.breweries_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BreweryViewHolder, position: Int) {
        holder.bind(getItem(position), breweryClickListener)
    }
}