package com.caiqueluz.buildsrc

object Dependencies {

    object Generic {

        val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Generic.KOTLIN}"
        val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.Generic.HILT}"

        val buildGradle = "com.android.tools.build:gradle:${Versions.Generic.BUILD_GRADLE}"
        val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Generic.KOTLIN}"
        val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.Generic.HILT}"
    }

    object Library {

        object Android {

            val lifecycleViewModel =
                "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Library.Android.LIFECYCLE}"
            val lifecycleLiveData =
                "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Library.Android.LIFECYCLE}"
            val lifecycleRuntime =
                "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Library.Android.LIFECYCLE}"

            val coreKtx = "androidx.core:core-ktx:${Versions.Library.Android.KTX}"
            val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.Library.Android.KTX}"

            val archCoreCommon = "androidx.arch.core:core-common:${Versions.Library.Android.ARCH}"
            val archCoreRuntime = "androidx.arch.core:core-runtime:${Versions.Library.Android.ARCH}"

            val hilt = "com.google.dagger:hilt-android:${Versions.Generic.HILT}"
            val material =
                "com.google.android.material:material:${Versions.Library.Android.MATERIAL}"
            val appCompat = "androidx.appcompat:appcompat:${Versions.Library.Android.APP_COMPAT}"
            val constraintLayout =
                "androidx.constraintlayout:constraintlayout:${Versions.Library.Android.CONSTRAINT_LAYOUT}"
            val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.Library.Android.VIEW_PAGER_2}"
        }

        val okHttp = "com.squareup.okhttp3:okhttp:${Versions.Library.OK_HTTP}"
        val okHttpLoggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.Library.OK_HTTP}"
        val gson = "com.google.code.gson:gson:${Versions.Library.GSON}"
        val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Library.RETROFIT}"
        val retrofitConverterGson =
            "com.squareup.retrofit2:converter-gson:${Versions.Library.RETROFIT}"
        val picasso = "com.squareup.picasso:picasso:${Versions.Library.PICASSO}"
    }

    object Test {

        val junit = "junit:junit:${Versions.Test.JUNIT}"
        val extJUnit = "androidx.test.ext:junit:${Versions.Test.EXT_JUNIT}"
        val mockitoCore = "org.mockito:mockito-core:${Versions.Test.MOCKITO_CORE}"
        val mockitoKotlin =
            "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.Test.MOCKITO_KOTLIN}"

        val archCoreTesting = "androidx.arch.core:core-testing:${Versions.Library.Android.ARCH}"
        val androidTestRunner = "androidx.test:runner:${Versions.Test.ANDROID_TEST_RUNNER}"

        val coroutinesTest =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Test.COROUTINES_TEST}"
        val espressoCore = "androidx.test.espresso:espresso-core:${Versions.Test.ESPRESSO_CORE}"
    }
}
