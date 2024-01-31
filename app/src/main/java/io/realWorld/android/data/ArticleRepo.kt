package io.realWorld.android.data

import io.realworld.api.RealWorldClient
import io.realworld.api.models.entities.Article
import io.realworld.api.models.entities.ArticleData
import io.realworld.api.models.requests.UpsertArticleRequest
import io.realworld.api.models.responses.ArticlesResponse
import retrofit2.Response
import java.lang.invoke.TypeDescriptor

object ArticleRepo {
    val api = RealWorldClient.publicApi
    val authApi = RealWorldClient.authApi
    suspend fun getGlobalFeed() = api.getArticles()
    suspend fun getMyFeed() = authApi.getFeedArticles()

    suspend fun getFavourites(): Response<ArticlesResponse> {
        val response = api.getArticles(favourited = "true")

        return if (response.isSuccessful) {
            val favoritedArticles = response.body()?.articles?.filter { it.favorited }
            val filteredResponse = favoritedArticles?.let { response.body()?.copy(articles = it) }
            Response.success(filteredResponse)
        } else {
            // Handle error
            response
        }

    }
    suspend fun createArticle(
        title : String?,
        description : String?,
        body: String?,
        tagList: List<String>?=null
    ):Article? {
        val response = authApi.createArticle(
            UpsertArticleRequest(ArticleData(
                title = title,
                body = body,
                description = description,
                tagList = tagList
            ))
        )
        return response.body()?.article

    }
}