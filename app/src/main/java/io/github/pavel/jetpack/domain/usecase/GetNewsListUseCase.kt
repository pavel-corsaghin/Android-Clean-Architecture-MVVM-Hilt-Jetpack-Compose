package io.github.pavel.jetpack.domain.usecase

import io.github.pavel.jetpack.domain.model.News
import io.github.pavel.jetpack.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsListUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend fun getNewsList(): Result<List<News>> {
        return newsRepository.getNewsList()
    }
}