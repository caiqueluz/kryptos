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
        //viewModel.fetchCurrencies()

        renderLoading()

        viewModel.currencies.observe(this) { response ->
            when (response) {
                is Loading -> renderLoading()
                is Content -> renderContent(data = response.content)
                is Error -> renderError(throwable = response.throwable)
            }
        }
    }

    private fun renderLoading() {
        binding.currenciesLoadingProgressbar.visibility = VISIBLE
    }

    private fun renderContent(data: CurrenciesVO) {
        // render content
        binding.currenciesLoadingProgressbar.visibility = GONE
    }

    private fun renderError(throwable: Throwable) {
        // render error
        binding.currenciesLoadingProgressbar.visibility = GONE
    }
}
