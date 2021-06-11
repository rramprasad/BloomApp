/*
 * Created by Ramprasad Ranganathan on 11/06/21, 8:54 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 11/06/21, 5:54 PM
 */

package dev.ramprasad.bloom.utils

sealed class Screen(val route:String) {
    object SplashScreen : Screen("SplashScreen")

    object AppBaseNavGraphRoute : Screen("AppBaseNavGraphRoute")
    object MainScreen : Screen("MainScreen")
    object HomeScreen : Screen("HomeScreen")
    object FavoritesScreen : Screen("FavoritesScreen")
    object UserProfileScreen : Screen("UserProfileScreen")
    object CartScreen : Screen("CartScreen")

    object LoginNavGraphRoute : Screen("LoginNavGraphRoute")
    object WelcomeScreen : Screen("WelcomeScreen")
    object LoginScreen : Screen("LoginScreen")
    //object HomeBaseScreen : Screen("HomeBaseScreen")
}