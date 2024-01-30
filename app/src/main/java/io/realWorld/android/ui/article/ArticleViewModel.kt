package io.realWorld.android.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.realWorld.android.data.ArticleRepo
import io.realworld.api.RealWorldClient
import io.realworld.api.models.entities.Article
import kotlinx.coroutines.launch

class ArticleViewModel:ViewModel() {

    val api = RealWorldClient.publicApi
    private val _article = MutableLiveData<Article>()
    val article : LiveData<Article> = _article

    fun fetchArticle(slug: String) = viewModelScope.launch {
        val response = api.getArticlesBySlug(slug)

        response.body()?.article?.let {
            _article.postValue(it)
        }
    }

    fun createArticle(
        title:String?,
        description:String?,
        body:String?,
        tagList:List<String>?=null
    ) =viewModelScope.launch {
        val article = ArticleRepo.createArticle(
            title=title,
            description = description,
            body=body,
            tagList = tagList
        )
    }

}