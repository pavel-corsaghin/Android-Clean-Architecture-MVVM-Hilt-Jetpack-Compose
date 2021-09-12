package io.github.pavel.jetpack.data.repository

import io.github.pavel.jetpack.data.model.response.NewsResponse
import io.github.pavel.jetpack.data.service.NewsService
import io.github.pavel.jetpack.domain.model.News
import io.github.pavel.jetpack.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsService: NewsService
) : NewsRepository {

    override suspend fun getNewsList(): Result<List<News>> {
//        val apiResponse = newsService.getNews().body()
//        if (apiResponse?.status != 200) {
//            throw Exception("An error occurred when get news")
//        }
//
//        return apiResponse.news ?: emptyList()

        // Todo: remove mock and use real handler above
        return Result.success(NewsResponse.mock().news ?: emptyList())
    }

    override suspend fun getNewsDetail(id: String): Result<News> {
        // Todo: remove mock and use real handler
        return Result.success(NewsResponse.mock().news?.find { it -> it.id == id }
            ?: throw Exception("An error occurred when get new detail"))
    }
}