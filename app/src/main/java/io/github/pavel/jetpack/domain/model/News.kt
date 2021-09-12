package io.github.pavel.jetpack.domain.model

data class News(
    val title: String?,
    val content: String?,
    val author: String?,
    val urlToImage: String?,
    val url: String
) {
    val id: Int
        get() = url.hashCode()
}