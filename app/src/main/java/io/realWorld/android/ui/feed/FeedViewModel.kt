package io.realWorld.android.ui.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.realWorld.android.data.ArticleRepo
import io.realworld.api.models.entities.Article
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {
    private val _feed = MutableLiveData<List<Article>>()
    val feed : LiveData<List<Article>> = _feed

    fun fetchGlobalFeed() = viewModelScope.launch{
        ArticleRepo.getGlobalFeed().body()?.let {
            _feed.postValue(it.articles)
            Log.d("feed","Global feed fetched ${it.articlesCount}")
        }
    }

    fun fetchMyFeed() = viewModelScope.launch {
        ArticleRepo.getMyFeed().body()?.let {
            _feed.postValue(it.articles)
            Log.d("feed","My feed fetched ${it.articlesCount}")
        }
    }

    fun fetchFav()= viewModelScope.launch {
        ArticleRepo.getFavourites().body()?.let{
            _feed.postValue(it.articles)
            Log.d("feed","favourite fetched ${it.articlesCount}")
        }
    }

}