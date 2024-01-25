package io.realworld.api.models.requests


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.realworld.api.models.entities.LoginData
import io.realworld.api.models.responses.User

@JsonClass(generateAdapter = true)
data class LoginUserRequest(
    @Json(name = "user")
    val user: LoginData
)