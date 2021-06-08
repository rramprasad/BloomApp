/*
 * Created by Ramprasad Ranganathan on 08/06/21, 4:09 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 08/06/21, 4:09 PM
 */

package dev.ramprasad.bloom

import android.util.Log
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
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.google.accompanist.insets.navigationBarsPadding
import dev.ramprasad.bloom.feature.home.HomeViewModel
import dev.ramprasad.bloom.feature.login.LoginViewModel
import dev.ramprasad.bloom.ui.*
import dev.ramprasad.bloom.utils.Screen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

@ExperimentalCoroutinesApi
@Composable
fun AppNavigation() {
    val appNavController = rememberNavController()

    AppNavigationGraph(appNavController)

    /*val loginViewModel = hiltViewModel<LoginViewModel>()
    if (loginViewModel.loginResultState.collectAsState().value) {
        HomeBaseScreen(appNavController)
    } else {
        appNavController.navigate(Screen.WelcomeScreen.route)
    }*/
}

@ExperimentalCoroutinesApi
@Composable
private fun AppNavigationGraph(appNavController: NavHostController) {
    NavHost(navController = appNavController, startDestination = Screen.HomeScreen.route) {
        composable(Screen.HomeScreen.route) {
            HomeBaseScreen(appNavController)
        }

        composable(Screen.FavoritesScreen.route) {
            FavoritesScreen()
        }

        composable(Screen.UserProfileScreen.route) {
            UserProfileScreen()
        }

        composable(Screen.CartScreen.route) {
            CartScreen()
        }

        navigation(Screen.WelcomeScreen.route,Screen.LoginNavGraphRoute.route) {
            composable(Screen.WelcomeScreen.route) {
                WelcomeScreen({
                    // On Create Account Clicked
                    /*Toast.makeText(
                        this@MainActivity,
                        "On Create Account Clicked",
                        Toast.LENGTH_SHORT
                    ).show()*/
                }) {
                    appNavController.navigate(Screen.LoginScreen.route)
                }
            }
            composable(Screen.LoginScreen.route) {
                LoginScreen {
                    appNavController.navigate(Screen.HomeScreen.route)
                }
            }
        }
    }
}


@Composable
fun HomeBaseScreen(appNavController: NavHostController) {
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
            BottomNavigation(appNavController)
            /*val appNavBackStackEntry by appNavController.currentBackStackEntryAsState()
            val currentRoute = appNavBackStackEntry?.destination?.route

            if(currentRoute != null && currentRoute != Screen.WelcomeScreen.route && currentRoute != Screen.LoginScreen.route) {
                BottomNavigation(appNavController)
            }*/
        },
        modifier = Modifier.navigationBarsPadding()
    ) {
        //HomeTabsSubNavigation(appNavController)
        //val homeViewModel = hiltViewModel<HomeViewModel>()
        //HomeScreen(homeViewModel = homeViewModel)

        val loginViewModel = hiltViewModel<LoginViewModel>()
        if (loginViewModel.loginResultState.collectAsState().value) {
            appNavController.navigate(Screen.HomeScreen.route)
        } else {
            appNavController.navigate(Screen.WelcomeScreen.route)
        }
    }
}

@Composable
private fun BottomNavigation(appNavController: NavHostController) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 16.dp,
        contentColor = MaterialTheme.colors.onPrimary,
        content = {
            val homeNavBackStackEntry by appNavController.currentBackStackEntryAsState()
            val currentRoute = homeNavBackStackEntry?.destination?.route

            BottomNavigationItem(
                icon = {
                    Icon(Icons.Filled.Home,null)
                },
                label = { Text(stringResource(id = R.string.home)) },
                selected = currentRoute == Screen.HomeScreen.route,
                onClick = {
                    appNavController.navigate(Screen.HomeScreen.route){
                        popUpTo = appNavController.graph.startDestinationId
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
                selected = currentRoute == Screen.FavoritesScreen.route,
                onClick = {
                    appNavController.navigate(Screen.FavoritesScreen.route){
                        popUpTo = appNavController.graph.startDestinationId
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
                selected = currentRoute == Screen.UserProfileScreen.route,
                onClick = {
                    appNavController.navigate(Screen.UserProfileScreen.route){
                        popUpTo = appNavController.graph.startDestinationId
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
                        Icons.Filled.ShoppingCart,
                        contentDescription = null
                    )
                },
                label = { Text(stringResource(id = R.string.cart)) },
                selected = currentRoute == Screen.CartScreen.route,
                onClick = {
                    appNavController.navigate(Screen.CartScreen.route){
                        popUpTo = appNavController.graph.startDestinationId
                        launchSingleTop = true
                    }
                },
                selectedContentColor = MaterialTheme.colors.onPrimary,
                unselectedContentColor = MaterialTheme.colors.secondary,
                alwaysShowLabel = true
            )
        }
    )
}