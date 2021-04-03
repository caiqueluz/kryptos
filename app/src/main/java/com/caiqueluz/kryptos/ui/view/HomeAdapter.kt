package com.caiqueluz.kryptos.ui.view

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.caiqueluz.kryptos.ui.vo.HomeItemVO

class HomePagerAdapter(
    containerFragment: Fragment,
    private val items: List<HomeItemVO>
) : FragmentStateAdapter(containerFragment) {

    override fun createFragment(position: Int): Fragment =
        items[position].fragment

    override fun getItemCount(): Int = items.size
}
