package com.caiqueluz.kryptos.ui

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.caiqueluz.kryptos.databinding.ActivityCurrenciesBinding
import com.caiqueluz.kryptos.network.NetworkResponse.*
import com.caiqueluz.kryptos.ui.viewmodel.CurrenciesListingVO
import com.caiqueluz.kryptos.ui.viewmodel.CurrenciesVO
import com.caiqueluz.kryptos.ui.viewmodel.CurrenciesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrenciesActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCurrenciesBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<CurrenciesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.fetchCurrenciesListing()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.currenciesListing.observe(this) { response ->
            when (response) {
                is Loading -> renderLoading()
                is Content -> fetchCurrenciesImages(response.content)
                is Error -> renderError(response.error)
            }
        }

        viewModel.currenciesImages.observe(this) { response ->
            when (response) {
                is Loading -> renderLoading()
                is Content -> renderContent(response.content)
                is Error -> renderError(response.error)
            }
        }
    }

    private fun fetchCurrenciesImages(content: CurrenciesListingVO) {
        val ids = content.currencies
            .map { it.id.toString() }
            .joinToString { it }
            .filterNot { it.isWhitespace() }

        viewModel.fetchCurrenciesImages(ids)
    }

    private fun renderLoading() {
        binding.currenciesLoadingProgressbar.visibility = VISIBLE
        binding.currenciesRecyclerView.visibility = GONE
    }

    private fun renderContent(data: CurrenciesVO) {
        binding.currenciesRecyclerView.visibility = VISIBLE
        binding.currenciesLoadingProgressbar.visibility = GONE

        binding.currenciesRecyclerView.adapter = CurrencyAdapter(
            currencies = data.currencies
        )
    }

    private fun renderError(error: Throwable) {
        binding.currenciesRecyclerView.visibility = GONE
        binding.currenciesLoadingProgressbar.visibility = GONE
    }
}
