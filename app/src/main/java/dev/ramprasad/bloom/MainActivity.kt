/*
 * Created by Ramprasad Ranganathan on 11/06/21, 8:54 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 11/06/21, 8:54 PM
 */

package dev.ramprasad.bloom

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.insets.ExperimentalAnimatedInsets
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint
import dev.ramprasad.bloom.theme.BloomTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @ExperimentalCoroutinesApi
    @ExperimentalAnimatedInsets
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            BloomTheme {
                ProvideWindowInsets(windowInsetsAnimationsEnabled = true,consumeWindowInsets = true) {
                    AppNavigation()
                }
            }
        }
    }
}