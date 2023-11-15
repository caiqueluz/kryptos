package com.caiqueluz.kryptos.ui.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.caiqueluz.kryptos.databinding.DialogErrorBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ErrorDialogFragment(
    private val onTryAgainButtonClicked: () -> Unit
) : BottomSheetDialogFragment() {

    private val binding by lazy {
        DialogErrorBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setCanceledOnTouchOutside(false)

        dialog.setupBottomSheetBehavior()
        setupTryAgainButtonClickListener(dialog)

        return dialog
    }

    private fun setupTryAgainButtonClickListener(dialog: Dialog) {
        binding.buttonTryAgain.setOnClickListener {
            dialog.dismiss()
            onTryAgainButtonClicked.invoke()
        }
    }
}
