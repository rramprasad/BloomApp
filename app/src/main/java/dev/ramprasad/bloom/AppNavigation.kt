/*
 * Created by Ramprasad Ranganathan on 11/06/21, 8:54 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 11/06/21, 8:54 PM
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
import com.google.accompanist.insets.navigationBarsPadding
import dev.ramprasad.bloom.feature.home.HomeViewModel
import dev.ramprasad.bloom.feature.login.LoginViewModel
import dev.ramprasad.bloom.ui.*
import dev.ramprasad.bloom.utils.Screen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun MainScreen() {
    val appNavController = rememberNavController()
    val loginNavController = rememberNavController()

    AppLoginNavigationGraph(appNavController, loginNavController)
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
        },
        modifier = Modifier.navigationBarsPadding()
    ) {
        AppMainNavigationGraph(appNavController, loginNavController)
    }
}

@ExperimentalCoroutinesApi
@Composable
private fun AppLoginNavigationGraph(
    appNavController: NavHostController,
    loginNavController: NavHostController
) {
    NavHost(navController = appNavController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) {
            val loginViewModel = hiltViewModel<LoginViewModel>()
            val loggedIn = loginViewModel.loginResultState.collectAsState().value

            SplashScreen {
                if (loggedIn) {
                    appNavController.navigate(Screen.HomeScreen.route) {
                        popUpTo(Screen.SplashScreen.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                } else {
                    loginNavController.navigate(Screen.WelcomeScreen.route) {
                        popUpTo(Screen.SplashScreen.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            }
        }

        composable(Screen.WelcomeScreen.route) {
            WelcomeScreen({
                // On Create Account Clicked
            }) {
                loginNavController.navigate(Screen.LoginScreen.route)
            }
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen {
                appNavController.navigate(Screen.HomeScreen.route) {
                    popUpTo(Screen.LoginScreen.route) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        }

        /*navigation(Screen.WelcomeScreen.route, Screen.LoginNavGraphRoute.route) {
            composable(Screen.WelcomeScreen.route) {
                WelcomeScreen({
                    // On Create Account Clicked
                }) {
                    appNavController.navigate(Screen.LoginScreen.route)
                }
            }
            composable(Screen.LoginScreen.route) {
                LoginScreen {
                    appNavController.navigate(Screen.MainScreen.route) {
                        popUpTo(Screen.LoginNavGraphRoute.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            }
        }*/
    }
}

@ExperimentalCoroutinesApi
@Composable
private fun AppMainNavigationGraph(
    appNavController: NavHostController,
    loginNavController: NavHostController
) {
    NavHost(navController = appNavController, startDestination = Screen.HomeScreen.route) {
        //val navGraphBuilder = this
        // Splash screen - Fixed Start destination
        /*composable(Screen.SplashScreen.route) {
            val loginViewModel = hiltViewModel<LoginViewModel>()
            val loggedIn = loginViewModel.loginResultState.collectAsState().value

            SplashScreen {
                if (loggedIn) {
                    appNavController.navigate(Screen.MainScreen.route) {
                        popUpTo(Screen.SplashScreen.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                } else {
                    appNavController.navigate(Screen.LoginNavGraphRoute.route) {
                        popUpTo(Screen.SplashScreen.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            }
        }*/

        /*composable(Screen.MainScreen.route) {
            MainScreen(appNavController = appNavController, navGraphBuilder)
        }*/

        composable(Screen.HomeScreen.route) {
            val homeViewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(homeViewModel = homeViewModel)

            val loginViewModel = hiltViewModel<LoginViewModel>()
            val loggedIn = loginViewModel.loginResultState.collectAsState().value
            if (!loggedIn) {
                loginNavController.navigate(Screen.WelcomeScreen.route)
            }
        }

        composable(Screen.FavoritesScreen.route) {
            val loginViewModel = hiltViewModel<LoginViewModel>()
            val loggedIn = loginViewModel.loginResultState.collectAsState().value
            if (!loggedIn) {
                loginNavController.navigate(Screen.WelcomeScreen.route)
            }
            FavoritesScreen()
        }

        composable(Screen.UserProfileScreen.route) {
            UserProfileScreen()
            val loginViewModel = hiltViewModel<LoginViewModel>()
            val loggedIn = loginViewModel.loginResultState.collectAsState().value
            if (!loggedIn) {
                loginNavController.navigate(Screen.WelcomeScreen.route)
            }
        }

        composable(Screen.CartScreen.route) {
            val loginViewModel = hiltViewModel<LoginViewModel>()
            val loggedIn = loginViewModel.loginResultState.collectAsState().value
            if (!loggedIn) {
                loginNavController.navigate(Screen.WelcomeScreen.route)
            }
        }

        /*navigation(Screen.WelcomeScreen.route, Screen.LoginNavGraphRoute.route) {
            composable(Screen.WelcomeScreen.route) {
                WelcomeScreen({
                    // On Create Account Clicked
                }) {
                    appNavController.navigate(Screen.LoginScreen.route)
                }
            }
            composable(Screen.LoginScreen.route) {
                LoginScreen {
                    appNavController.navigate(Screen.MainScreen.route) {
                        popUpTo(Screen.LoginNavGraphRoute.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            }
        }*/
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
            val currentDestination = homeNavBackStackEntry?.destination

            BottomNavigationItem(
                icon = {
                    Icon(Icons.Filled.Home, null)
                },
                label = { Text(stringResource(id = R.string.home)) },
                selected = currentDestination?.hierarchy?.any { it.route == Screen.HomeScreen.route } == true,
                onClick = {
                    appNavController.navigate(Screen.HomeScreen.route) {
                        popUpTo(appNavController.graph.startDestinationId) {
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
                    appNavController.navigate(Screen.FavoritesScreen.route) {
                        popUpTo(appNavController.graph.startDestinationId) {
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
                    appNavController.navigate(Screen.UserProfileScreen.route) {
                        popUpTo(appNavController.graph.startDestinationId) {
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
                        Icons.Filled.ShoppingCart,
                        contentDescription = null
                    )
                },
                label = { Text(stringResource(id = R.string.cart)) },
                selected = currentDestination?.hierarchy?.any { it.route == Screen.CartScreen.route } == true,
                onClick = {
                    appNavController.navigate(Screen.CartScreen.route) {
                        popUpTo(appNavController.graph.startDestinationId) {
                            inclusive = true
                        }
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