package com.caiqueluz.kryptos.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.caiqueluz.kryptos.databinding.ActivityHomeBinding
import com.caiqueluz.kryptos.ui.viewmodel.HomeViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.createHomeItems()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.items.observe(this) { items ->
            val tabs = items.map { it.tab }
            val fragments = items.map { it.fragment }

            setupLayout(tabs, fragments)
        }
    }

    private fun setupLayout(tabs: List<String>, fragments: List<Fragment>) {
        with(binding) {
            val adapter = HomeAdapter(this@HomeActivity, fragments)
            homeViewPager.adapter = adapter

            TabLayoutMediator(homeTabContainer, homeViewPager) { tab, position ->
                tab.text = tabs[position]
            }.attach()
        }
    }
}
