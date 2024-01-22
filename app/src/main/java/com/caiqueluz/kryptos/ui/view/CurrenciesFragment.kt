package com.caiqueluz.kryptos.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.caiqueluz.kryptos.databinding.FragmentCurrenciesBinding
import com.caiqueluz.kryptos.network.NetworkResponse
import com.caiqueluz.kryptos.ui.viewmodel.CurrenciesViewModel
import com.caiqueluz.kryptos.ui.vo.CurrenciesVO
import com.caiqueluz.kryptos.ui.vo.CurrencyDetailDialogVO
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class CurrenciesFragment : Fragment() {

    private val binding by lazy {
        FragmentCurrenciesBinding.inflate(layoutInflater)
    }

    private val viewModel by inject<CurrenciesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onScreenStarted()
        setupObservers()
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.currencies.collect { response ->
                    when (response) {
                        is NetworkResponse.Loading -> renderLoading()
                        is NetworkResponse.Content -> renderContent(response.content)
                        is NetworkResponse.Error -> renderError()
                    }
                }
            }
        }
    }

    private fun renderLoading() {
        binding.currenciesLoadingProgressbar.visibility = View.VISIBLE
        binding.currenciesRecyclerView.visibility = View.GONE
    }

    private fun renderContent(data: CurrenciesVO) {
        binding.currenciesRecyclerView.visibility = View.VISIBLE
        binding.currenciesLoadingProgressbar.visibility = View.GONE

        binding.currenciesRecyclerView.adapter = CurrencyAdapter(
            currencies = data.currencies,
            onItemClicked = ::onPriceInformationItemClicked
        )
    }

    private fun onPriceInformationItemClicked(detailDialogVO: CurrencyDetailDialogVO) {
        CurrencyDetailDialogFragment(detailDialogVO)
            .show(
                parentFragmentManager, null
            )
    }

    private fun renderError() {
        binding.currenciesRecyclerView.visibility = View.GONE
        binding.currenciesLoadingProgressbar.visibility = View.GONE

        ErrorDialogFragment(
            onTryAgainButtonClicked = viewModel::onErrorModalTryAgainButtonClicked
        ).show(parentFragmentManager, null)
    }
}
