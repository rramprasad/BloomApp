package dev.ramprasad.bloom

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