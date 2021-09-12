package io.github.pavel.jetpack.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import io.github.pavel.jetpack.R
import io.github.pavel.jetpack.data.model.response.NewsResponse
import io.github.pavel.jetpack.domain.model.News
import io.github.pavel.jetpack.ui.base.AppComposable
import io.github.pavel.jetpack.ui.theme.NewsApplicationTheme
import io.github.pavel.jetpack.util.NavDestinations

@Composable
fun ListScreen(
    navController: NavController,
    viewModel: ListViewModel = hiltViewModel()
) {
    val newsList by viewModel.newsList.observeAsState(initial = emptyList())
    viewModel.getNewsList()
    AppComposable(
        viewModel = viewModel,
        content = { ListScreen(navController, newsList) }
    )
}

@Composable
fun ListScreen(
    navController: NavController,
    newsList: List<News>
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cryptocurrency news") },
            )
        }
    )
    {
        LazyColumn {
            items(newsList) { news ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("${NavDestinations.DETAIL_SCREEN}/${news.id}")
                        },
                ) {
                    Column {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(16f / 9f),
                            painter = rememberImagePainter(
                                data = news.urlToImage,
                                builder = {
                                    placeholder(R.drawable.placeholder)
                                    error(R.drawable.placeholder)
                                }
                            ),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth
                        )
                        Column(Modifier.padding(8.dp)) {
                            Text(
                                news.title ?: "",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 2
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListPreview() {
    NewsApplicationTheme {
        ListScreen(
            navController = rememberNavController(),
            newsList = NewsResponse.mock().articles ?: emptyList()
        )
    }
}