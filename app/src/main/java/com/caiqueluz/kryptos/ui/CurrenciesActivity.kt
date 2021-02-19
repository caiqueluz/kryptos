package com.caiqueluz.kryptos.ui

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.caiqueluz.kryptos.databinding.ActivityCurrenciesBinding
import com.caiqueluz.kryptos.network.NetworkResponse.*
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

        setupObserver()
    }

    private fun setupObserver() {
        viewModel.currencies.start(Unit)

        viewModel.currencies.observe(this) { response ->
            when (response) {
                is Loading -> renderLoading()
                is Content -> renderContent(response.content)
                is Error -> renderError(response.error)
            }
        }
    }

    private fun renderLoading() {
        binding.currenciesLoadingProgressbar.visibility = VISIBLE
        binding.currenciesContentView.visibility = GONE
    }

    private fun renderContent(data: CurrenciesVO) {
        binding.currenciesContentView.visibility = VISIBLE
        binding.currenciesLoadingProgressbar.visibility = GONE

        binding.currenciesRecyclerView.adapter = CurrencyAdapter(
            currencies = data.currencies
        )
    }

    private fun renderError(error: Throwable) {
        binding.currenciesContentView.visibility = GONE
        binding.currenciesLoadingProgressbar.visibility = GONE
    }
}
