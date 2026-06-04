package com.aravind.popularmovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.aravind.popularmovies.ui.navigation.PopularMoviesNavGraph
import com.aravind.popularmovies.ui.theme.PopularMoviesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PopularMoviesTheme {
                PopularMoviesNavGraph()
            }
        }
    }
}
