package com.caiqueluz.kryptos.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.caiqueluz.kryptos.databinding.FragmentCurrenciesBinding
import com.caiqueluz.kryptos.network.NetworkResponse
import com.caiqueluz.kryptos.ui.viewmodel.CurrenciesViewModel
import com.caiqueluz.kryptos.ui.vo.CurrenciesVO
import com.caiqueluz.kryptos.ui.vo.CurrencyDetailDialogVO
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

        viewModel.fetchCurrencies()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.currencies.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResponse.Loading -> renderLoading()
                is NetworkResponse.Content -> renderContent(response.content)
                is NetworkResponse.Error -> renderError(response.error)
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
            onItemClickAction = ::onPriceInformationClickAction
        )
    }

    private fun onPriceInformationClickAction(
        detailDialogVO: CurrencyDetailDialogVO
    ) {
        CurrencyDetailDialogFragment(detailDialogVO)
            .show(
                parentFragmentManager, null
            )
    }

    private fun renderError(error: Throwable) {
        binding.currenciesRecyclerView.visibility = View.GONE
        binding.currenciesLoadingProgressbar.visibility = View.GONE

        ErrorDialogFragment { viewModel.fetchCurrencies() }
            .show(
                parentFragmentManager, null
            )
    }
}
