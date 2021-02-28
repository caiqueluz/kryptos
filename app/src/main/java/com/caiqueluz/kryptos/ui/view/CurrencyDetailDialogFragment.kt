package com.caiqueluz.kryptos.ui.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.caiqueluz.kryptos.databinding.DialogCurrencyDetailBinding
import com.caiqueluz.kryptos.ui.domain.CurrencyDetailDialogVO
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CurrencyDetailDialogFragment(
    private val detailDialogVO: CurrencyDetailDialogVO
) : BottomSheetDialogFragment() {

    private val binding by lazy {
        DialogCurrencyDetailBinding.inflate(layoutInflater)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        setupBottomSheetBehavior(dialog)

        return dialog
    }

    private fun setupBottomSheetBehavior(dialog: Dialog) {
        dialog.setOnShowListener {
            val bottomSheetContainer = dialog.findViewById<View>(
                com.google.android.material.R.id.design_bottom_sheet
            )

            val behavior = BottomSheetBehavior.from(bottomSheetContainer)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
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
        binding.currencyDetailName.text = detailDialogVO.name
        binding.currencyDetailSymbol.text = detailDialogVO.symbol

        detailDialogVO.image?.let { image ->
            binding.currencyDetailImage.setImageBitmap(image)
        }
    }
}
