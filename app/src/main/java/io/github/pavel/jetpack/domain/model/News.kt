package io.github.pavel.jetpack.domain.model

data class News(
    var id: String,
    var title: String?,
    var content: String?,
    var author: String?,
    var imageUrl: String?,
    var url: String
)