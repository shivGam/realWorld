package io.realWorld.android.data

import io.realworld.api.RealWorldClient

object ArticleRepo {
    val api = RealWorldClient().api
    suspend fun getGlobalFeed() = api.getArticles()
}