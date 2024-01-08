package com.caiqueluz.kryptos.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.caiqueluz.kryptos.databinding.ActivityHomeBinding
import com.caiqueluz.kryptos.ui.viewmodel.HomeViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    private val viewModel by inject<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.onScreenStarted()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.items.observe(this) { items ->
            val fragments = items.map { it.toHomeTabFragment() }
            val tabs = items.map { resources.getString(it.tabResId) }

            setupAdapter(fragments)
            setupTabs(tabs)
        }
    }

    private fun setupAdapter(fragments: List<Fragment>) {
        val adapter = HomeAdapter(this@HomeActivity, fragments)
        binding.homeViewPager.adapter = adapter
    }

    private fun setupTabs(tabs: List<String>) {
        with(binding) {
            TabLayoutMediator(homeTabContainer, homeViewPager) { tab, position ->
                tab.text = tabs[position]
            }.attach()
        }
    }
}
