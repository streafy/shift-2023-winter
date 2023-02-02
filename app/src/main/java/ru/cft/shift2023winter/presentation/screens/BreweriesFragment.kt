package ru.cft.shift2023winter.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.cft.shift2023winter.R
import ru.cft.shift2023winter.presentation.BreweriesViewModel

@AndroidEntryPoint
class BreweriesFragment : Fragment(R.layout.fragment_breweries) {
    private var recycler: RecyclerView? = null
    private val breweryAdapter = BreweryAdapter()

    private val viewModel: BreweriesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler = view.findViewById(R.id.breweries)
        recycler?.adapter = breweryAdapter
        recycler?.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPage().collectLatest { pagingData ->
                breweryAdapter.submitData(pagingData)
            }
        }
    }

    override fun onDestroy() {
        recycler?.adapter = null
        super.onDestroy()
    }
}