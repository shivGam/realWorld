package io.realWorld.android.data

import io.realworld.api.RealWorldClient

object ArticleRepo {
    val api = RealWorldClient.publicApi
    val authApi = RealWorldClient.authApi
    suspend fun getGlobalFeed() = api.getArticles()
    suspend fun getMyFeed() = authApi.getFeedArticles()
}