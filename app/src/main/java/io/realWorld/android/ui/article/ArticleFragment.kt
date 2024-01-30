package io.realWorld.android.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.realWorld.android.R
import io.realWorld.android.databinding.FragmentArticleBinding

class ArticleFragment:Fragment() {
    private var _binding: FragmentArticleBinding ?= null
    private lateinit var articleViewModel : ArticleViewModel
    private var articleId: String?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleBinding.inflate(inflater,container,false)
        articleViewModel = ViewModelProvider(this)[ArticleViewModel::class.java]
        arguments?.let {
            articleId = it.getString(resources.getString(R.string.args_article_id))
        }
        articleId?.let { articleViewModel.fetchArticle(it) }
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articleViewModel.article.observe(viewLifecycleOwner){
            _binding?.apply {
                tvTitle.text = it.title
                tvAuthor.text = it.author.username
                tvBody.text = it.body
                tvDate.text = it.createdAt
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}