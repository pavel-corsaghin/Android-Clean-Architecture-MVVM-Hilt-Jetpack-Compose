package io.github.pavel.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.pavel.jetpack.data.model.response.NewsResponse
import io.github.pavel.jetpack.ui.detail.DetailScreen
import io.github.pavel.jetpack.ui.list.ListScreen
import io.github.pavel.jetpack.ui.theme.NewsApplicationTheme
import io.github.pavel.jetpack.util.NavDestinations

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = NavDestinations.LIST_SCREEN,
                    ) {
                        composable(NavDestinations.LIST_SCREEN) {
                            ListScreen(navController)
                        }
                        composable("${NavDestinations.DETAIL_SCREEN}/{newsId}") {
                            it.arguments?.getString("newsId")?.let { newsId ->
                                DetailScreen(newsId, navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsApplicationTheme {
        ListScreen(
            navController = rememberNavController(),
            newsList = NewsResponse.mock().news ?: emptyList()
        )
    }
}