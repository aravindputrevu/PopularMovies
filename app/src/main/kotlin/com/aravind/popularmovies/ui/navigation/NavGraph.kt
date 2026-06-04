package com.aravind.popularmovies.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aravind.popularmovies.data.model.Movie
import com.aravind.popularmovies.ui.screens.details.DetailsScreen
import com.aravind.popularmovies.ui.screens.home.HomeScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder

object Routes {
    const val HOME = "home"
    const val DETAILS = "details/{movieJson}"

    fun details(movie: Movie): String {
        val json = URLEncoder.encode(Json.encodeToString(movie), "UTF-8")
        return "details/$json"
    }
}

@Composable
fun PopularMoviesNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) {
            HomeScreen(
                onMovieClick = { movie ->
                    navController.navigate(Routes.details(movie))
                }
            )
        }
        composable(
            route = Routes.DETAILS,
            arguments = listOf(navArgument("movieJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val movieJson = backStackEntry.arguments?.getString("movieJson") ?: return@composable
            val decoded = URLDecoder.decode(movieJson, "UTF-8")
            val movie = remember(decoded) { Json.decodeFromString<Movie>(decoded) }
            DetailsScreen(
                movie = movie,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
