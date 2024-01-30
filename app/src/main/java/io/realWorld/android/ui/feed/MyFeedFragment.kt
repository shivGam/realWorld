package io.realWorld.android.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import io.realWorld.android.R
import io.realWorld.android.databinding.FeedFragmentBinding

class MyFeedFragment: Fragment() {
    private lateinit var viewModel: FeedViewModel
    private var _binding : FeedFragmentBinding? = null
    private lateinit var feedAdapter: ArticleFeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[FeedViewModel::class.java]
        _binding = FeedFragmentBinding.inflate(inflater,container,false)
        _binding?.feedRecycler?.layoutManager = LinearLayoutManager(context)
        feedAdapter = ArticleFeedAdapter {openArticle(it)}
        _binding?.feedRecycler?.adapter = feedAdapter
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchMyFeed()

        viewModel.feed.observe(viewLifecycleOwner) {
            feedAdapter.submitList(it)
        }
    }
    private fun openArticle(articleId: String) {
        findNavController().navigate(
            R.id.action_myFeedFragment_to_articleFragment,
            bundleOf(
                resources.getString(R.string.args_article_id) to articleId
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}