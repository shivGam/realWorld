package io.realWorld.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.realWorld.android.data.UserRepo
import io.realworld.api.models.entities.User
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user : LiveData<User?> = _user

    fun login(email: String, password: String) = viewModelScope.launch {
        UserRepo.login(email, password)?.let {
            _user.postValue(it.user)
        }
    }
    fun signup(username:String,email:String,password: String) = viewModelScope.launch {
        UserRepo.signup(username,email,password)?.let {
            _user.postValue(it.user)
        }
    }
    fun update(
        bio:String?,
        email: String?,
        image: String?,
        password: String?,
        username: String?
    ) = viewModelScope.launch{
        UserRepo.update(bio,email,image,username,password)?.let {
            _user.postValue(it.user)
        }
    }
}
