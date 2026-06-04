package com.aravind.popularmovies.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aravind.popularmovies.data.MovieRepository
import com.aravind.popularmovies.data.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class MovieTab { POPULAR, TOP_RATED }

data class HomeUiState(
    val popularMovies: List<Movie> = emptyList(),
    val topRatedMovies: List<Movie> = emptyList(),
    val selectedTab: MovieTab = MovieTab.POPULAR,
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadMovies()
    }

    fun selectTab(tab: MovieTab) {
        _uiState.value = _uiState.value.copy(selectedTab = tab)
    }

    fun retry() {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                val popular = repository.getPopularMovies()
                val topRated = repository.getTopRatedMovies()
                _uiState.value = _uiState.value.copy(
                    popularMovies = popular,
                    topRatedMovies = topRated,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load movies"
                )
            }
        }
    }
}
