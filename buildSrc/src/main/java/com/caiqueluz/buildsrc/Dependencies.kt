package com.caiqueluz.buildsrc

object Dependencies {

    object Generic {

        const val KOTLIN_STD_LIB = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Generic.KOTLIN}"
        const val BUILD_GRADLE = "com.android.tools.build:gradle:${Versions.Generic.BUILD_GRADLE}"
        const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Generic.KOTLIN}"
    }

    object Library {

        object Android {

            const val LIFECYCLE_VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Library.Android.LIFECYCLE}"
            const val LIFECYCLE_LIVE_DATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Library.Android.LIFECYCLE}"
            const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Library.Android.LIFECYCLE}"

            const val CORE_KTX = "androidx.core:core-ktx:${Versions.Library.Android.KTX}"
            const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Versions.Library.Android.KTX}"

            const val ARCH_CORE_COMMON = "androidx.arch.core:core-common:${Versions.Library.Android.ARCH}"
            const val ARCH_CORE_RUNTIME = "androidx.arch.core:core-runtime:${Versions.Library.Android.ARCH}"

            const val MATERIAL = "com.google.android.material:material:${Versions.Library.Android.MATERIAL}"
            const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.Library.Android.APP_COMPAT}"
            const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.Library.Android.CONSTRAINT_LAYOUT}"
            const val VIEW_PAGER_2 = "androidx.viewpager2:viewpager2:${Versions.Library.Android.VIEW_PAGER_2}"
        }

        const val KOIN_GRADLE_PLUGIN = "org.koin:koin-gradle-plugin:${Versions.Library.KOIN}"

        const val OK_HTTP = "com.squareup.okhttp3:okhttp:${Versions.Library.OK_HTTP}"
        const val OK_HTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.Library.OK_HTTP}"
        const val GSON = "com.google.code.gson:gson:${Versions.Library.GSON}"
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.Library.RETROFIT}"
        const val RETROFIT_CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${Versions.Library.RETROFIT}"
        const val PICASSO = "com.squareup.picasso:picasso:${Versions.Library.PICASSO}"

        const val KOIN_CORE = "org.koin:koin-core:${Versions.Library.KOIN}"
        const val KOIN_SCOPE = "org.koin:koin-androidx-scope:${Versions.Library.KOIN}"
        const val KOIN_VIEW_MODEL = "org.koin:koin-androidx-viewmodel:${Versions.Library.KOIN}"
        const val KOIN_FRAGMENT = "org.koin:koin-androidx-fragment:${Versions.Library.KOIN}"
    }

    object Test {

        const val JUNIT = "junit:junit:${Versions.Test.JUNIT}"
        const val EXT_JUNIT = "androidx.test.ext:junit:${Versions.Test.EXT_JUNIT}"
        const val MOCKITO_CORE = "org.mockito:mockito-core:${Versions.Test.MOCKITO_CORE}"
        const val MOCKITO_KOTLIN = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.Test.MOCKITO_KOTLIN}"
        const val MOCKITO_ANDROID = "org.mockito:mockito-android:${Versions.Test.MOCKITO_CORE}"

        const val ARCH_CORE_TESTING = "androidx.arch.core:core-testing:${Versions.Library.Android.ARCH}"
        const val FRAGMENT_TESTING = "androidx.fragment:fragment-testing:${Versions.Test.FRAGMENT_TESTING}"
        const val ANDROID_TEST_RUNNER = "androidx.test:runner:${Versions.Test.ANDROID_TEST_RUNNER}"

        const val COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Test.COROUTINES_TEST}"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.Test.ESPRESSO_CORE}"
        const val KOIN_TEST = "org.koin:koin-test:${Versions.Library.KOIN}"
        const val ROBOLECTRIC = "org.robolectric:robolectric:${Versions.Test.ROBOLECTRIC}"
    }
}
