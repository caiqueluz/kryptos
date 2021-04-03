package com.caiqueluz.kryptos.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.caiqueluz.kryptos.databinding.ActivityHomeBinding

class CurrenciesActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
