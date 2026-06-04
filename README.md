# Popular Movies

An Android app that shows the most popular and top-rated movies of all time, powered by [The Movie Database (TMDB)](https://www.themoviedb.org/) API.

## Tech Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose with Material 3
- **Architecture**: MVVM (ViewModel + Repository + StateFlow)
- **Networking**: Retrofit + OkHttp + Kotlin Coroutines
- **Serialization**: Kotlinx Serialization
- **Image Loading**: Coil
- **DI**: Hilt
- **Navigation**: Compose Navigation
- **Build**: Gradle 8.x with Kotlin DSL + Version Catalog

## Setup

1. Get a TMDB API key at https://www.themoviedb.org/documentation/api
2. Add your key to `local.properties`:
   ```
   TMDB_API_KEY=your_api_key_here
   ```
3. Build and run:
   ```
   ./gradlew assembleDebug
   ```

## Project Structure

```
app/src/main/kotlin/com/aravind/popularmovies/
├── data/
│   ├── model/          # Data classes (Movie, MovieResponse)
│   ├── remote/         # Retrofit API interface
│   └── MovieRepository.kt
├── di/                 # Hilt dependency injection module
├── ui/
│   ├── navigation/     # Compose Navigation graph
│   ├── screens/
│   │   ├── home/       # Movie grid with tabs (Popular / Top Rated)
│   │   └── details/    # Movie detail view
│   └── theme/          # Material 3 theme (supports dynamic color)
├── MainActivity.kt
└── PopularMoviesApp.kt
```

## Min SDK

Android 7.0 (API 24)
