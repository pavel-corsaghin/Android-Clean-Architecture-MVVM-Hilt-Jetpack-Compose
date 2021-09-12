package io.github.pavel.jetpack.domain.usecase

import io.github.pavel.jetpack.domain.model.News
import io.github.pavel.jetpack.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsDetailUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend fun getNewsDetail(id: String): Result<News> {
        return newsRepository.getNewsDetail(id)
    }
}

