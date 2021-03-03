package com.caiqueluz.kryptos.ui.view

import android.app.Dialog
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior

fun Dialog.setupBottomSheetBehavior() {
    this.setOnShowListener {
        val bottomSheetContainer = this.findViewById<View>(
            com.google.android.material.R.id.design_bottom_sheet
        )

        val behavior = BottomSheetBehavior.from(bottomSheetContainer)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }
}
