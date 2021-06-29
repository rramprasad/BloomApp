/*
 * Created by Ramprasad Ranganathan on 29/06/21, 2:44 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 29/06/21, 2:02 PM
 */

package dev.ramprasad.bloom.utils

sealed class Screen(val route:String) {
    object SplashScreen : Screen("SplashScreen")
    object WelcomeScreen : Screen("WelcomeScreen")
    object LoginScreen : Screen("LoginScreen")
    object SignUpScreen : Screen("SignUpScreen")
    object MainScreen : Screen("MainScreen")
    object HomeScreen : Screen("HomeScreen")
    object FavoritesScreen : Screen("FavoritesScreen")
    object UserProfileScreen : Screen("UserProfileScreen")
    object CartScreen : Screen("CartScreen")
}