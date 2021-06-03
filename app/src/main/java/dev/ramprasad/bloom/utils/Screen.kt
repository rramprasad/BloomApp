/*
 * Created by Ramprasad Ranganathan on 03/06/21, 5:49 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 02/06/21, 2:08 PM
 */

package dev.ramprasad.bloom.utils

sealed class Screen(val route:String) {
    object LandingScreen : Screen("LandingScreen")
    object WelcomeScreen : Screen("WelcomeScreen")
    object LoginScreen : Screen("LoginScreen")
    object HomeBaseScreen : Screen("HomeBaseScreen")

    object HomeScreen : Screen("HomeScreen")
    object FavoritesScreen : Screen("FavoritesScreen")
    object UserProfileScreen : Screen("UserProfileScreen")
    object CartScreen : Screen("CartScreen")
}