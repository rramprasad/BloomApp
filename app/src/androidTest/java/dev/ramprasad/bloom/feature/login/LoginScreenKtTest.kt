/*
 * Created by Ramprasad Ranganathan on 15/07/21, 4:04 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 15/07/21, 4:04 PM
 */

package dev.ramprasad.bloom.feature.login

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import dev.ramprasad.bloom.MainActivity
import dev.ramprasad.bloom.R
import dev.ramprasad.bloom.ui.LoginScreen
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginScreenKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            LoginScreen() {

            }
        }
    }

    @After
    fun tearDown() {
    }

    @Test
    fun loginScreen() {
    }

    @Test
    fun loginTitle() {
        composeTestRule.onNode(hasText("Log in with email"))
            .assert(hasText("Log in with email"))
            .assert(hasNoClickAction())
            .assertExists()
    }

    @Test
    fun emailTextField() {
    }

    @Test
    fun passwordTextField() {
    }

    @Test
    fun termsOfUseText() {
    }

    @Test
    fun loginInButton() {
    }
}