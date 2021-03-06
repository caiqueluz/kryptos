package com.caiqueluz.kryptos.ui.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.caiqueluz.kryptos.databinding.DialogCurrencyDetailBinding
import com.caiqueluz.kryptos.ui.domain.CurrencyDetailDialogVO
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CurrencyDetailDialogFragment(
    private val detailDialogVO: CurrencyDetailDialogVO
) : BottomSheetDialogFragment() {

    private val binding by lazy {
        DialogCurrencyDetailBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setupBottomSheetBehavior()

        return dialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupLayout()
    }

    private fun setupLayout() {
        binding.currencyDetailName.text = detailDialogVO.name
        binding.currencyDetailSymbol.text = detailDialogVO.symbol
        binding.currencyDetailLastUpdatedDate.text = detailDialogVO.lastUpdatedDate

        detailDialogVO.image?.let { image ->
            binding.currencyDetailImage.setImageBitmap(image)
        }
    }
}
