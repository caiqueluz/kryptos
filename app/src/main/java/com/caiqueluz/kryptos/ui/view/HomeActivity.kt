package com.caiqueluz.kryptos.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.caiqueluz.kryptos.databinding.ActivityHomeBinding
import com.caiqueluz.kryptos.ui.viewmodel.HomeViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    private val viewModel by inject<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupObservers()
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.items.collect { items ->
                    val fragments = items.map { it.toHomeTabFragment() }
                    val tabs = items.map { resources.getString(it.tabResId) }

                    setupAdapter(fragments)
                    setupTabs(tabs)
                }
            }
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
