package io.realworld.api.services

import io.realworld.api.models.requests.UserUpdateRequest
import io.realworld.api.models.responses.ArticlesResponse
import io.realworld.api.models.responses.ProfileResponse
import io.realworld.api.models.responses.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RealWorldAuthAPI {

    @GET("user")
    suspend fun getCurrentUser(): Response<UserResponse>

    @PUT("user")
    suspend fun updateCurrentUser(
        @Body userUpdateRequest: UserUpdateRequest
    ):Response<UserResponse>

    @GET("profile/{username}")
    suspend fun getProfile(
        @Path("username") username:String
    ):Response<ProfileResponse>

    @POST("profile/{username}/follow")
    suspend fun followProfile(
        @Path("username") username:String
    ):Response<ProfileResponse>

    @DELETE("profile/{username}/follow")
    suspend fun unfollowProfile(
        @Path("username") username:String
    ):Response<ProfileResponse>

    @GET("articles/feed")
    suspend fun getFeedArticles(): Response<ArticlesResponse>
}