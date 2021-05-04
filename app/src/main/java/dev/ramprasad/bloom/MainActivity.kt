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
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.google.accompanist.insets.ExperimentalAnimatedInsets
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import dagger.hilt.android.AndroidEntryPoint
import dev.ramprasad.bloom.ui.*
import dev.ramprasad.bloom.ui.theme.BloomTheme
import dev.ramprasad.bloom.viewmodel.HomeViewModel

@AndroidEntryPoint
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
        NavHost(navController = homeNavController, startDestination = Screen.HomeScreen.route) {
            composable(Screen.HomeScreen.route) {
                val homeViewModel = hiltNavGraphViewModel<HomeViewModel>(backStackEntry = it)
                HomeScreen(homeViewModel)
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
