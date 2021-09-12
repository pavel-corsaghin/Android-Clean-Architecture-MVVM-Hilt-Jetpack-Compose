package io.github.pavel.jetpack.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import io.github.pavel.jetpack.data.model.response.NewsResponse
import io.github.pavel.jetpack.domain.model.News
import io.github.pavel.jetpack.ui.base.AppComposable
import io.github.pavel.jetpack.ui.composable.WebPageView
import io.github.pavel.jetpack.ui.list.ListScreen
import io.github.pavel.jetpack.ui.theme.NewsApplicationTheme

@Composable
fun DetailScreen(
    newsId: String,
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val news by viewModel.news.observeAsState(initial = null)
    val showSmartMode by viewModel.showSmartMode.observeAsState(initial = true)
    viewModel.getNewsById(newsId)
    AppComposable(
        viewModel = viewModel,
        content = {
            DetailScreen(
                navController,
                news,
                showSmartMode,
                toggleMode = { viewModel.toggleMode() })
        }
    )
}

@Composable
fun DetailScreen(
    navController: NavController,
    news: News?,
    showSmartMode: Boolean,
    toggleMode: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(news?.title ?: "", maxLines = 1, overflow = TextOverflow.Ellipsis) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
                actions = {
                    TextButton(
                        onClick = toggleMode
                    ) {
                        Text(text = if (showSmartMode) "WEB" else "SMART", color = Color.White)
                    }
                }
            )
        }
    ) {
        news?.let {
            if (showSmartMode) {
                SmartView(news = it)
            } else {
                WebPageView(urlToRender = it.url)
            }
        } ?: run {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailsPreview() {
    NewsApplicationTheme {
        DetailScreen(
            navController = rememberNavController(),
            news = NewsResponse.mock().news?.firstOrNull(),
            showSmartMode = true,
            toggleMode = {}
        )
    }
}
