package io.realWorld.android.data

import io.realworld.api.RealWorldClient
import io.realworld.api.models.entities.LoginData
import io.realworld.api.models.entities.SignupData
import io.realworld.api.models.entities.User
import io.realworld.api.models.entities.UserUpdateData
import io.realworld.api.models.requests.LoginUserRequest
import io.realworld.api.models.requests.SignupRequest
import io.realworld.api.models.requests.UserUpdateRequest
import io.realworld.api.models.responses.UserResponse

object UserRepo {
    val api = RealWorldClient.publicApi
    val authApi = RealWorldClient.authApi

    suspend fun login(email: String, password: String): UserResponse? {
        val response = api.loginUser(LoginUserRequest(LoginData(email, password)))
        RealWorldClient.authToken = response.body()?.user?.token
        return response.body()

    }

    suspend fun signup(username: String, email: String, password: String): UserResponse? {
        val response = api.signupUsers(SignupRequest(SignupData(email, password, username)))
        RealWorldClient.authToken = response.body()?.user?.token
        return response.body()
    }
    suspend fun update(bio:String?, email: String?, image: String?, password: String?, username: String?):UserResponse?{

        val response = authApi.updateCurrentUser(UserUpdateRequest(UserUpdateData(
            bio, email, image, password, username
        )))
        return response.body()
    }

    suspend fun getCurrentUser(token: String) :User? {
        RealWorldClient.authToken = token
        return authApi.getCurrentUser().body()?.user
    }
}