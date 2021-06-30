/*
 * Created by Ramprasad Ranganathan on 29/06/21, 2:44 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 29/06/21, 2:44 PM
 */

package dev.ramprasad.bloom

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.google.accompanist.insets.navigationBarsPadding
import dev.ramprasad.bloom.feature.home.HomeScreen
import dev.ramprasad.bloom.feature.login.LoginViewModel
import dev.ramprasad.bloom.feature.login.SignUpScreen
import dev.ramprasad.bloom.ui.*
import dev.ramprasad.bloom.utils.Screen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun AppNavigation() {
    val appNavController = rememberNavController()

    NavHost(navController = appNavController, startDestination = Screen.SplashScreen.route) {
        // Splash screen
        composable(Screen.SplashScreen.route) {
            val loginViewModel = hiltViewModel<LoginViewModel>()
            val loggedIn = loginViewModel.userLoggedIn.collectAsState().value

            SplashScreen {
                if (loggedIn) {
                    appNavController.navigate(Screen.MainScreen.route) {
                        popUpTo(Screen.SplashScreen.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                } else {
                    appNavController.navigate("LoginSubNavigationGraphRoute") {
                        popUpTo(Screen.SplashScreen.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            }
        }

        navigation(Screen.WelcomeScreen.route, "LoginSubNavigationGraphRoute") {
            // Login Welcome screen
            composable(Screen.WelcomeScreen.route) {
                WelcomeScreen({
                    appNavController.navigate(Screen.SignUpScreen.route)
                }) {
                    appNavController.navigate(Screen.LoginScreen.route)
                }
            }

            // Login screen
            composable(Screen.LoginScreen.route) {
                LoginScreen {
                    appNavController.navigate(Screen.MainScreen.route) {
                        popUpTo("LoginSubNavigationGraphRoute") {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            }

            // SignUp screen
            composable(Screen.SignUpScreen.route) {
                SignUpScreen {
                    appNavController.navigate(Screen.MainScreen.route) {
                        popUpTo("LoginSubNavigationGraphRoute") {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            }
        }

        // Main screen
        composable(Screen.MainScreen.route) {
            MainScreen(appNavController)
        }
    }
}

@Composable
fun MainScreen(appNavController: NavHostController) {
    val mainScreenNavController = rememberNavController()
    Scaffold(
        drawerBackgroundColor = MaterialTheme.colors.primary,
        drawerContent = {

        },
        drawerContentColor = MaterialTheme.colors.onBackground,
        drawerElevation = 4.dp,
        drawerGesturesEnabled = true,
        drawerScrimColor = MaterialTheme.colors.secondary,
        drawerShape = MaterialTheme.shapes.medium,
        bottomBar = {
            BottomNavigation(mainScreenNavController)
        },
        modifier = Modifier.navigationBarsPadding()
    ) {
        NavHost(mainScreenNavController, startDestination = Screen.HomeScreen.route) {
            composable(Screen.HomeScreen.route) {
                HomeScreen()
                val loginViewModel = hiltViewModel<LoginViewModel>()
                val loggedIn = loginViewModel.userLoggedIn.collectAsState().value
                if (!loggedIn) {
                    appNavController.navigate(Screen.WelcomeScreen.route)
                }
            }

            composable(Screen.FavoritesScreen.route) {
                FavoritesScreen()
                val loginViewModel = hiltViewModel<LoginViewModel>()
                val loggedIn = loginViewModel.userLoggedIn.collectAsState().value
                if (!loggedIn) {
                    appNavController.navigate(Screen.WelcomeScreen.route)
                }
            }

            composable(Screen.UserProfileScreen.route) {
                UserProfileScreen()
                val loginViewModel = hiltViewModel<LoginViewModel>()
                val loggedIn = loginViewModel.userLoggedIn.collectAsState().value
                if (!loggedIn) {
                    appNavController.navigate(Screen.WelcomeScreen.route)
                }
            }

            composable(Screen.CartScreen.route) {
                CartScreen()
                val loginViewModel = hiltViewModel<LoginViewModel>()
                val loggedIn = loginViewModel.userLoggedIn.collectAsState().value
                if (!loggedIn) {
                    appNavController.navigate(Screen.WelcomeScreen.route)
                }
            }
        }
    }
}

@Composable
private fun BottomNavigation(mainScreenNavController: NavHostController) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 16.dp,
        contentColor = MaterialTheme.colors.onPrimary,
        content = {
            val homeNavBackStackEntry by mainScreenNavController.currentBackStackEntryAsState()
            val currentDestination = homeNavBackStackEntry?.destination

            BottomNavigationItem(
                icon = {
                    Icon(Icons.Filled.Home, null)
                },
                label = { Text(stringResource(id = R.string.home)) },
                selected = currentDestination?.hierarchy?.any { it.route == Screen.HomeScreen.route } == true,
                onClick = {
                    mainScreenNavController.navigate(Screen.HomeScreen.route) {
                        popUpTo(Screen.HomeScreen.route){
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                selectedContentColor = MaterialTheme.colors.onPrimary,
                unselectedContentColor = MaterialTheme.colors.secondary,
                alwaysShowLabel = true
            )

            BottomNavigationItem(
                icon = {
                    Icon(
                        Icons.Outlined.FavoriteBorder,
                        contentDescription = null
                    )
                },
                label = { Text(stringResource(id = R.string.favorites)) },
                selected = currentDestination?.hierarchy?.any { it.route == Screen.FavoritesScreen.route } == true,
                onClick = {
                    mainScreenNavController.navigate(Screen.FavoritesScreen.route) {
                        popUpTo(Screen.FavoritesScreen.route){
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                selectedContentColor = MaterialTheme.colors.onPrimary,
                unselectedContentColor = MaterialTheme.colors.secondary,
                alwaysShowLabel = true
            )

            BottomNavigationItem(
                icon = {
                    Icon(
                        Icons.Filled.AccountCircle,
                        contentDescription = null
                    )
                },
                label = { Text(stringResource(id = R.string.profile)) },
                selected = currentDestination?.hierarchy?.any { it.route == Screen.UserProfileScreen.route } == true,
                onClick = {
                    mainScreenNavController.navigate(Screen.UserProfileScreen.route) {
                        popUpTo(Screen.UserProfileScreen.route){
                            inclusive = true
                        }
                    }
                },
                selectedContentColor = MaterialTheme.colors.onPrimary,
                unselectedContentColor = MaterialTheme.colors.secondary,
                alwaysShowLabel = true
            )

            BottomNavigationItem(
                icon = {
                    Icon(
                        Icons.Filled.ShoppingCart,
                        contentDescription = null
                    )
                },
                label = { Text(stringResource(id = R.string.cart)) },
                selected = currentDestination?.hierarchy?.any { it.route == Screen.CartScreen.route } == true,
                onClick = {
                    mainScreenNavController.navigate(Screen.CartScreen.route) {
                        popUpTo(Screen.CartScreen.route){
                            inclusive = true
                        }
                    }
                },
                selectedContentColor = MaterialTheme.colors.onPrimary,
                unselectedContentColor = MaterialTheme.colors.secondary,
                alwaysShowLabel = true
            )
        }
    )
}