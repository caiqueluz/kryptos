package com.caiqueluz.kryptos

import android.content.Context
import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ApplicationProvider

val testResources: Resources =
    ApplicationProvider.getApplicationContext<Context>().resources

inline fun <reified FRAGMENT : Fragment> launchFragment() =
    launchFragmentInContainer<FRAGMENT>(themeResId = R.style.Theme_Kryptos)
