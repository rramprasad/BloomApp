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
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import dev.ramprasad.bloom.data.GardenTheme
import dev.ramprasad.bloom.data.Plant
import dev.ramprasad.bloom.ui.*
import dev.ramprasad.bloom.ui.theme.BloomTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BloomTheme {
                AppMainNavigation()
            }
        }
    }

    @Composable
    private fun AppMainNavigation() {
        val navController = rememberNavController()
        NavHost(navController, "LandingScreen") {
            composable("LandingScreen") {
                LandingScreen {
                    navController.navigate("WelcomeScreen") {
                        popUpTo("LandingScreen") {
                            inclusive = true
                        }
                    }
                }
            }
            composable("WelcomeScreen") {
                WelcomeScreen({
                    // On Create Account Clicked
                    Toast.makeText(
                        this@MainActivity,
                        "On Create Account Clicked",
                        Toast.LENGTH_SHORT
                    ).show()
                }) {
                    navController.navigate("LoginScreen")
                }
            }
            composable("LoginScreen") {
                LoginScreen {
                    navController.navigate("HomeBase")
                }
            }
            composable("HomeBase") {
                HomeBase()
                HomeNavigation()
            }
        }
    }

    @Composable
    private fun HomeNavigation() {
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

        val homeNavController = rememberNavController()
        NavHost(navController = homeNavController, startDestination = "HomeScreen") {
            composable("HomeScreen") {
                HomeScreen(gardenThemesList, plantsList)
            }

            composable("FavoritesScreen") {
                FavoritesScreen()
            }

            composable("UserProfileScreen") {
                UserProfileScreen()
            }

            composable("CartScreen") {
                CartScreen()
            }
        }
    }

    @Composable
    private fun HomeBase() {
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
                BottomNavigation()
            },
        ) {

        }
    }
}

@Composable
private fun BottomNavigation() {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 16.dp,
        contentColor = MaterialTheme.colors.onPrimary,
        content = {
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(
                            id = R.drawable.ic_baseline_home_24
                        ),
                        contentDescription = null
                    )
                },
                label = { Text(stringResource(id = R.string.home)) },
                selected = true,
                onClick = {
                },
                selectedContentColor = MaterialTheme.colors.onPrimary,
                unselectedContentColor = MaterialTheme.colors.secondary,
                alwaysShowLabel = true
            )

            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(
                            id = R.drawable.ic_baseline_favorite_border_24
                        ),
                        contentDescription = null
                    )
                },
                label = { Text(stringResource(id = R.string.favorites)) },
                selected = false,
                onClick = {

                },
                selectedContentColor = MaterialTheme.colors.onPrimary,
                unselectedContentColor = MaterialTheme.colors.secondary,
                alwaysShowLabel = true
            )

            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(
                            id = R.drawable.ic_baseline_account_circle_24
                        ),
                        contentDescription = null
                    )
                },
                label = { Text(stringResource(id = R.string.profile)) },
                selected = false,
                onClick = {

                },
                selectedContentColor = MaterialTheme.colors.onPrimary,
                unselectedContentColor = MaterialTheme.colors.secondary,
                alwaysShowLabel = true
            )

            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(
                            id = R.drawable.ic_baseline_shopping_cart_24
                        ),
                        contentDescription = null
                    )
                },
                label = { Text(stringResource(id = R.string.cart)) },
                selected = false,
                onClick = {

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
