package io.github.pavel.jetpack.domain.repository

import io.github.pavel.jetpack.domain.model.News

interface NewsRepository {
    suspend fun getNewsList(): List<News>

    suspend fun getNewsDetail(id: String): News
}