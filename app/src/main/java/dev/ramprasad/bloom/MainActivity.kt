/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.ramprasad.bloom

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.google.accompanist.insets.ExperimentalAnimatedInsets
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import dev.ramprasad.bloom.data.GardenTheme
import dev.ramprasad.bloom.data.Plant
import dev.ramprasad.bloom.ui.*
import dev.ramprasad.bloom.ui.theme.BloomTheme

class MainActivity : AppCompatActivity() {

    @ExperimentalAnimatedInsets
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BloomTheme {
                ProvideWindowInsets(windowInsetsAnimationsEnabled = true,consumeWindowInsets = true) {
                    AppMainNavigation()
                }
            }
        }
    }

    @Composable
    private fun AppMainNavigation() {
        val navController = rememberNavController()
        NavHost(navController, Screen.LandingScreen.route) {
            composable(Screen.LandingScreen.route) {
                LandingScreen {
                    navController.navigate(Screen.WelcomeScreen.route) {
                        popUpTo(Screen.LandingScreen.route) {
                            inclusive = true
                        }
                    }
                }
            }
            composable(Screen.WelcomeScreen.route) {
                WelcomeScreen({
                    // On Create Account Clicked
                    Toast.makeText(
                        this@MainActivity,
                        "On Create Account Clicked",
                        Toast.LENGTH_SHORT
                    ).show()
                }) {
                    navController.navigate(Screen.LoginScreen.route)
                }
            }
            composable(Screen.LoginScreen.route) {
                LoginScreen {
                    navController.navigate(Screen.HomeBaseScreen.route)
                }
            }
            composable(Screen.HomeBaseScreen.route) {
                HomeBase()
            }
        }
    }

    @Composable
    private fun HomeBase() {
        val homeNavController = rememberNavController()

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
                BottomNavigation(homeNavController)
            },
            modifier = Modifier.navigationBarsPadding()
        ) {
            HomeNavigation(homeNavController)
        }
    }

    @Composable
    private fun HomeNavigation(homeNavController: NavHostController) {
        val gardenThemesList = arrayListOf(
            GardenTheme(1, stringResource(R.string.desert_chic),"https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Themes/theme1.jpg"),
            GardenTheme(2, stringResource(R.string.tiny_terrariums),"https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Themes/theme2.jpg"),
            GardenTheme(3, stringResource(R.string.jungle_vibes),"https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Themes/theme3.jpg"),
            GardenTheme(4, stringResource(R.string.easy_care),"https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Themes/theme4.jpg"),
            GardenTheme(5, stringResource(R.string.statements),"https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Themes/theme5.jpg")
        )
        val plantsList = arrayListOf(
            Plant(1, stringResource(R.string.monstera),"https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Plants/plant1.jpg",stringResource(R.string.plant_description)),
            Plant(2, stringResource(R.string.aglaonema),"https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Plants/plant2.jpg",stringResource(R.string.plant_description)),
            Plant(3, stringResource(R.string.peace_lily),"https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Plants/plant3.jpg",stringResource(R.string.plant_description)),
            Plant(4, stringResource(R.string.fiddle_leaf_tree),"https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Plants/plant4.jpg",stringResource(R.string.plant_description)),
            Plant(5, stringResource(R.string.snake_plant),"https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Plants/plant5.jpg",stringResource(R.string.plant_description)),
            Plant(6, stringResource(R.string.pothos),"https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Plants/plant6.jpg",stringResource(R.string.plant_description))
        )
        NavHost(navController = homeNavController, startDestination = Screen.HomeScreen.route) {
            composable(Screen.HomeScreen.route) {
                HomeScreen(gardenThemesList, plantsList)
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
        }
    }


}

@Composable
private fun BottomNavigation(homeNavController: NavHostController) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 16.dp,
        contentColor = MaterialTheme.colors.onPrimary,
        content = {
            val homeNavBackStackEntry by homeNavController.currentBackStackEntryAsState()
            val currentRoute = homeNavBackStackEntry?.arguments?.getString(KEY_ROUTE)

            BottomNavigationItem(
                icon = {
                    Icon(Icons.Filled.Home,null)
                },
                label = { Text(stringResource(id = R.string.home)) },
                selected = currentRoute == Screen.HomeScreen.route,
                onClick = {
                    homeNavController.navigate(Screen.HomeScreen.route){
                        popUpTo = homeNavController.graph.startDestination
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
                    homeNavController.navigate(Screen.FavoritesScreen.route){
                        popUpTo = homeNavController.graph.startDestination
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
                    homeNavController.navigate(Screen.UserProfileScreen.route){
                        popUpTo = homeNavController.graph.startDestination
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
                    homeNavController.navigate(Screen.CartScreen.route){
                        popUpTo = homeNavController.graph.startDestination
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


/*@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}*/
