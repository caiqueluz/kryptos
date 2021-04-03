package com.caiqueluz.kryptos.ui.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeAdapter(
    containerActivity: FragmentActivity,
    private val items: List<Fragment>
) : FragmentStateAdapter(containerActivity) {

    override fun createFragment(position: Int): Fragment =
        items[position]

    override fun getItemCount(): Int = items.size
}
