package com.caiqueluz.kryptos.ui.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.caiqueluz.kryptos.ui.HomeFragmentFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    factory: HomeFragmentFactory
) : ViewModel() {

    private val _fragments = MutableLiveData<Unit>()
    val fragments: LiveData<List<Fragment>> = _fragments.map {
        factory.create()
    }

    fun createFragments() {
        _fragments
    }
}
