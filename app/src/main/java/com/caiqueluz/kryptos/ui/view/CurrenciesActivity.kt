package com.caiqueluz.kryptos.ui.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.caiqueluz.kryptos.databinding.ActivityCurrenciesBinding
import com.caiqueluz.kryptos.network.NetworkResponse.*
import com.caiqueluz.kryptos.ui.domain.CurrenciesVO
import com.caiqueluz.kryptos.ui.domain.CurrencyDetailDialogVO
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

        viewModel.fetchCurrencies()
        setupObservers()
    }

    private fun setupObservers() {
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
        binding.currenciesRecyclerView.visibility = GONE
    }

    private fun renderContent(data: CurrenciesVO) {
        binding.currenciesRecyclerView.visibility = VISIBLE
        binding.currenciesLoadingProgressbar.visibility = GONE

        binding.currenciesRecyclerView.adapter = CurrencyAdapter(
            currencies = data.currencies,
            onItemClickAction = ::onPriceInformationClickAction
        )
    }

    private fun onPriceInformationClickAction(
        detailDialogVO: CurrencyDetailDialogVO
    ) {
        CurrencyDetailDialogFragment(detailDialogVO)
            .show(
                supportFragmentManager, null
            )
    }

    private fun renderError(error: Throwable) {
        binding.currenciesRecyclerView.visibility = GONE
        binding.currenciesLoadingProgressbar.visibility = GONE

        ErrorDialogFragment { viewModel.fetchCurrencies() }
            .show(
                supportFragmentManager, null
            )
    }
}
