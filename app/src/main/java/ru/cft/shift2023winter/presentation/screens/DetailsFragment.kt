package ru.cft.shift2023winter.presentation.screens

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ru.cft.shift2023winter.R
import ru.cft.shift2023winter.domain.entity.Brewery
import ru.cft.shift2023winter.presentation.DetailsUiState
import ru.cft.shift2023winter.presentation.DetailsViewModel

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var progressBar: ProgressBar
    private lateinit var errorMessage: TextView
    private lateinit var detailsContent: LinearLayout

    private lateinit var name: TextView
    private lateinit var address: TextView
    private lateinit var city: TextView
    private lateinit var counrty: TextView
    private lateinit var phone: TextView
    private lateinit var website: TextView
    private lateinit var backButton: Button

    private val args: DetailsFragmentArgs by navArgs()

    private val viewModel: DetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progress_bar)
        errorMessage = view.findViewById(R.id.error_message)
        detailsContent = view.findViewById(R.id.details_content)

        name = view.findViewById(R.id.name)
        address = view.findViewById(R.id.address)
        city = view.findViewById(R.id.city)
        counrty = view.findViewById(R.id.country)
        phone = view.findViewById(R.id.phone)
        website = view.findViewById(R.id.website)
        backButton = view.findViewById(R.id.backButton)

        lifecycleScope.launchWhenStarted {
            viewModel.state
                .collect(::handleState)
        }

        viewModel.loadData(args.breweryId)
    }

    private fun handleState(state: DetailsUiState) {
        when (state) {
            DetailsUiState.Initial    -> Unit
            DetailsUiState.Loading    -> showProgress()
            is DetailsUiState.Content -> showContent(state.brewery)
            is DetailsUiState.Error   -> showError(state.message)
        }
    }

    private fun showProgress() {
        progressBar.isVisible = true
        errorMessage.isVisible = false
        detailsContent.isVisible = false
    }

    private fun showContent(brewery: Brewery) {
        progressBar.isVisible = false
        errorMessage.isVisible = false
        detailsContent.isVisible = true

        name.text = brewery.name
        address.text = brewery.street
        city.text = brewery.city
        counrty.text = brewery.country
        phone.text = brewery.phone
        website.text = brewery.websiteUrl

        backButton.setOnClickListener {
            val navController = findNavController()

            val action = DetailsFragmentDirections.actionDetailsFragmentToBreweriesFragment()
            navController.navigate(action)
        }
    }

    private fun showError(message: String?) {
        errorMessage.isVisible = true
        progressBar.isVisible = false
        detailsContent.isVisible = false

        errorMessage.text = message ?: "unknown error"
    }
}