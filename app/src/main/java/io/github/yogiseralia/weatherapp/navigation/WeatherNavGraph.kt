package io.github.yogiseralia.weatherapp.navigation

import androidx.activity.OnBackPressedDispatcher
import androidx.activity.addCallback
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.github.yogiseralia.weatherapp.citylist.CityListScreen
import io.github.yogiseralia.weatherapp.currentweather.screens.CityWeatherScreen

@Composable
fun WeatherNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String,
    onBackPressedDispatcher: OnBackPressedDispatcher,
    onFinish: () -> Unit
) {
    val screenSharedViewModel: ScreenSharedViewModel =
        viewModel(factory = ScreenSharedViewModel.Factory)

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.CITY_WEATHER.route) {
            CityWeatherScreen(
                screenSharedViewModel.cityName,
                onHamburgerCLick = {
                    with(navController) {
                        navigate(Screen.CITY_LIST.route)
                    }
                }
            )
        }
        composable(Screen.CITY_LIST.route) {
            CityListScreen(
                onBack = { navController.popBackStack() },
                onCitySelected = { cityName ->
                    screenSharedViewModel.cityName = cityName
                    navController.popBackStack()
                },
                onAddCity = { navController.navigate(Screen.ADD_CITY.name) }
            )
        }
    }

    onBackPressedDispatcher.addCallback {
        if (navController.currentBackStackEntry?.destination?.route == Screen.CITY_WEATHER.route) {
            onFinish()
        } else {
            navController.popBackStack()
        }
    }
}