package com.caiqueluz.kryptos.ui.view

import android.app.Dialog
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.caiqueluz.kryptos.databinding.DialogCurrencyDetailBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CurrencyDetailDialogFragment(
    private val currencyImage: Bitmap?,
    private val currencyName: String,
    private val currencySymbol: String
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

        setupLayout(currencyImage)
    }

    private fun setupLayout(currencyImage: Bitmap?) {
        binding.currencyDetailName.text = currencyName
        binding.currencyDetailSymbol.text = currencySymbol

        currencyImage?.let { image ->
            binding.currencyDetailImage.setImageBitmap(image)
        }
    }
}
