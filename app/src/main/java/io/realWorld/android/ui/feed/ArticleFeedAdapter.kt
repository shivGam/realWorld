package io.realWorld.android.ui.feed

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.realWorld.android.R
import io.realWorld.android.databinding.ListItemArticleBinding
import io.realWorld.android.ui.extensions.loadImage
import io.realworld.api.models.entities.Article

class ArticleFeedAdapter(val onArticleClicked:(slug : String) -> Unit) :ListAdapter<Article,ArticleFeedAdapter.ArticleViewHolder>(
    object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.toString() == newItem.toString()
        }
    }
) {
    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            parent.context.getSystemService(LayoutInflater::class.java).inflate(
                R.layout.list_item_article,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        ListItemArticleBinding.bind(holder.itemView).apply {
            val article = getItem(position)

            tvName.text = article.author.username
            tvTitle.text = article.title
            tvDesc.text = article.description
            tvDate.text = article.createdAt
            ivProfile.loadImage(article.author.image)

            root.setOnClickListener { onArticleClicked(article.slug) }
        }
    }
}