package io.realworld.api.services

import io.realworld.api.models.requests.LoginUserRequest
import io.realworld.api.models.requests.SignupRequest
import io.realworld.api.models.responses.ArticlesReponse
import io.realworld.api.models.responses.TagResponse
import io.realworld.api.models.responses.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RealWorldAPI {

    @POST("users")
    suspend fun signupUsers(
        @Body userCreds: SignupRequest
    ):Response<UserResponse>

    @POST("users/login")
    suspend fun loginUser(
        @Body userCreds: LoginUserRequest
    ):Response<UserResponse>

    @GET("articles")
    suspend fun getArticles(
        @Query("author") author:String? =null,
        @Query("favourited") favourited :String?=null,
        @Query("tag") tag : String?=null,
    ): Response<ArticlesReponse>

    @GET("articles/{slug}")
    suspend fun getArticlesBySlug(
        @Path("slug") slug :String
    ):Response<ArticlesReponse>

    @GET("tags")
    suspend fun getTags() : Response<TagResponse>
}
