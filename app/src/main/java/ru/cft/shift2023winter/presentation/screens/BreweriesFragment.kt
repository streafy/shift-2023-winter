package ru.cft.shift2023winter.presentation.screens

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.cft.shift2023winter.R
import ru.cft.shift2023winter.domain.entity.Brewery
import ru.cft.shift2023winter.presentation.BreweriesUiState
import ru.cft.shift2023winter.presentation.BreweriesViewModel
import ru.cft.shift2023winter.presentation.adapters.BreweryAdapter
import ru.cft.shift2023winter.presentation.adapters.BreweryLoadStateAdapter

@AndroidEntryPoint
class BreweriesFragment : Fragment(R.layout.fragment_breweries) {

    private lateinit var progressBar: ProgressBar
    private lateinit var errorMessage: TextView
    private lateinit var content: LinearLayout

    private lateinit var recycler: RecyclerView
    private val breweryAdapter = BreweryAdapter(::handleBreweryClick)

    private val viewModel: BreweriesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progress_bar)
        errorMessage = view.findViewById(R.id.error_message)
        content = view.findViewById(R.id.breweries_content)

        recycler = view.findViewById(R.id.breweries)


        lifecycleScope.launchWhenStarted {
            viewModel.state
                .collect(::handleState)
        }

        viewModel.loadData()
    }

    private fun setupRecycler() {
        recycler.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        recycler.adapter = breweryAdapter.withLoadStateFooter(
            footer = BreweryLoadStateAdapter(breweryAdapter::retry)
        )
        breweryAdapter.addLoadStateListener { state ->
            val isLoading = state.refresh == LoadState.Loading
            content.isVisible = !isLoading
            progressBar.isVisible = isLoading
        }
    }

    private fun setupRequests(breweries: Flow<PagingData<Brewery>>) {
        viewLifecycleOwner.lifecycleScope.launch {
            breweries.collectLatest {
                breweryAdapter.submitData(it)
            }
        }
    }

    private fun handleBreweryClick(brewery: Brewery) {
        val navController = findNavController()

        val action = BreweriesFragmentDirections.actionBreweriesFragmentToDetailsFragment(brewery.id)
        navController.navigate(action)
    }

    private fun handleState(state: BreweriesUiState) {
        when (state) {
            BreweriesUiState.Initial    -> Unit
            BreweriesUiState.Loading    -> showProgress()
            is BreweriesUiState.Content -> showContent(state.breweries)
            is BreweriesUiState.Error   -> showError(state.message)
        }
    }

    private fun showProgress() {
        progressBar.isVisible = true
        errorMessage.isVisible = false
        content.isVisible = false
    }

    private fun showContent(breweries: Flow<PagingData<Brewery>>) {
        progressBar.isVisible = false
        errorMessage.isVisible = false
        content.isVisible = true

        setupRecycler()
        setupRequests(breweries)
    }

    private fun showError(message: String?) {
        errorMessage.isVisible = true
        progressBar.isVisible = false
        content.isVisible = false

        errorMessage.text = message ?: "unknown error"
    }

    override fun onDestroy() {
        recycler.adapter = null
        super.onDestroy()
    }
}