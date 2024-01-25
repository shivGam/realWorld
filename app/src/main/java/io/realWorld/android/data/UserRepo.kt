package io.realWorld.android.data

import io.realworld.api.RealWorldClient
import io.realworld.api.models.entities.LoginData
import io.realworld.api.models.requests.LoginUserRequest
import io.realworld.api.models.responses.UserResponse

object UserRepo {
    val api = RealWorldClient().api
    suspend fun login(email : String,password : String) : UserResponse?{
        val response = api.loginUser(LoginUserRequest(LoginData(email,password)))
        return response.body()
    }
}