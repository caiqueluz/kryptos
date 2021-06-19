package com.caiqueluz.kryptos.ui.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.caiqueluz.kryptos.databinding.DialogCurrencyDetailBinding
import com.caiqueluz.kryptos.ui.vo.CurrencyDetailDialogVO
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLayout()
    }

    private fun setupLayout() {
        with(binding) {
            currencyDetailName.text = detailDialogVO.name
            currencyDetailSymbol.text = detailDialogVO.symbol
            currencyDetailLastUpdatedDate.text = detailDialogVO.lastUpdatedDate

            detailDialogVO.image?.let { image ->
                currencyDetailImage.setImageBitmap(image)
            }
        }
    }
}
