package com.caiqueluz.kryptos.ui.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.caiqueluz.kryptos.databinding.DialogCurrencyPriceDetailBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CurrencyPriceDetailDialogFragment(
    private val currencyName: String,
    private val currencySymbol: String
) : BottomSheetDialogFragment() {

    private val binding by lazy {
        DialogCurrencyPriceDetailBinding.inflate(layoutInflater)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        dialog.setOnShowListener {
            val layout = dialog.findViewById<View>(
                com.google.android.material.R.id.design_bottom_sheet
            ) as FrameLayout

            val behavior = BottomSheetBehavior.from(layout)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupLayout()
    }

    private fun setupLayout() {
        binding.currencyPriceDetailName.text = currencyName
        binding.currencyPriceDetailSymbol.text = currencySymbol
    }
}
