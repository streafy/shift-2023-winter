package ru.cft.shift2023winter.presentation.screens

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.cft.shift2023winter.R
import ru.cft.shift2023winter.domain.entity.Brewery
import ru.cft.shift2023winter.presentation.BreweriesUiState
import ru.cft.shift2023winter.presentation.BreweriesViewModel

@AndroidEntryPoint
class BreweriesFragment() : Fragment(R.layout.fragment_breweries) {

    private val progressBar get() = requireView().findViewById<ProgressBar>(R.id.progress_bar)
    private val errorText get() = requireView().findViewById<TextView>(R.id.error_text)
    private val breweriesContent get() = requireView().findViewById<LinearLayout>(R.id.breweries_content)

    private var breweryList: RecyclerView? = null

    private val viewModel: BreweriesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        breweryList = view.findViewById(R.id.breweries)
        breweryList?.adapter = BreweriesAdapter(::handleBreweryClick)
        breweryList?.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        viewModel.state.observe(viewLifecycleOwner, ::handleState)

        viewModel.loadDate()
    }

    private fun handleState(state: BreweriesUiState) {
        when (state) {
            BreweriesUiState.Initial -> Unit
            BreweriesUiState.Loading -> showProgress()
            is BreweriesUiState.Content -> showContent(state.breweries)
            is BreweriesUiState.Error -> showError(state.message)
        }
    }

    private fun showContent(breweries: List<Brewery>) {
        progressBar.isVisible = false
        errorText.isVisible = false
        breweriesContent.isVisible = true
        (breweryList?.adapter as? BreweriesAdapter)?.breweries = breweries
    }

    private fun handleBreweryClick(brewery: Brewery) {
        TODO()
    }

    private fun showProgress() {
        progressBar.isVisible = true
        errorText.isVisible = false
        breweriesContent.isVisible = false
    }

    private fun showError(message: String?) {
        errorText.isVisible = true
        progressBar.isVisible = false
        breweriesContent.isVisible = false

        errorText.text = message ?: "unknown"
    }

    override fun onDestroy() {
        breweryList?.adapter = null
        breweryList = null
        super.onDestroy()
    }
}