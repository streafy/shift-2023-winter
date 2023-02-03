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
import ru.cft.shift2023winter.R
import ru.cft.shift2023winter.domain.entity.Brewery
import ru.cft.shift2023winter.presentation.DetailsUiState
import ru.cft.shift2023winter.presentation.DetailsViewModel

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val progressBar get() = requireView().findViewById<ProgressBar>(R.id.progress_bar)
    private val errorMessage get() = requireView().findViewById<TextView>(R.id.error_message)
    private val detailsContent get() = requireView().findViewById<LinearLayout>(R.id.details_content)

    private val name = requireView().findViewById<TextView>(R.id.name)
    private val address = requireView().findViewById<TextView>(R.id.address)
    private val phone = requireView().findViewById<TextView>(R.id.phone)
    private val website = requireView().findViewById<TextView>(R.id.website)

    private val viewModel: DetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.state
                .collect(::handleState)
        }

        viewModel.loadData()
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
        phone.text = brewery.phone
        website.text = brewery.websiteUrl
    }

    private fun showError(message: String?) {
        errorMessage.isVisible = true
        progressBar.isVisible = false
        detailsContent.isVisible = false

        errorMessage.text = message ?: "unknown error"
    }
}