package io.github.pavel.jetpack.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import io.github.pavel.jetpack.R
import io.github.pavel.jetpack.domain.model.News

@Composable
fun SmartView(
    news: News,
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        Column {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                painter = rememberImagePainter(
                    data = news.imageUrl,
                    builder = {
                        placeholder(R.drawable.placeholder)
                        error(R.drawable.placeholder)
                    }
                ),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Column(Modifier.padding(8.dp)) {
                Text(news.title ?: "", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(news.content ?: "")
            }
        }
    }
}

