package ru.cft.shift2023winter.presentation.screens

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.cft.shift2023winter.R
import ru.cft.shift2023winter.domain.entity.Brewery
import ru.cft.shift2023winter.presentation.BreweriesViewModel
import ru.cft.shift2023winter.presentation.adapters.BreweryAdapter
import ru.cft.shift2023winter.presentation.adapters.BreweryLoadStateAdapter

@AndroidEntryPoint
class BreweriesFragment : Fragment(R.layout.fragment_breweries) {
    private var recycler: RecyclerView? = null
    private val breweryAdapter = BreweryAdapter(::handleBreweryClick)

    private val viewModel: BreweriesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler = view.findViewById(R.id.breweries)
        recycler?.adapter = breweryAdapter.withLoadStateFooter(
            footer = BreweryLoadStateAdapter(breweryAdapter::retry)
        )
        recycler?.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPage().collectLatest { pagingData ->
                breweryAdapter.submitData(pagingData)
            }
        }

        val content = view.findViewById<LinearLayout>(R.id.breweries_content)
        val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)
        breweryAdapter.addLoadStateListener { state ->
            content.isVisible = state.refresh != LoadState.Loading
            progressBar.isVisible = state.refresh == LoadState.Loading
        }
    }

    private fun handleBreweryClick(brewery: Brewery) {
        val navController = findNavController()

        val action = BreweriesFragmentDirections.actionBreweriesFragmentToDetailsFragment(brewery.id)
        navController.navigate(action)
    }

    override fun onDestroy() {
        recycler?.adapter = null
        super.onDestroy()
    }
}