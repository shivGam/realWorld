package io.realWorld.android.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.realWorld.android.databinding.FeedFragmentBinding

class GlobalFeedFragment :Fragment() {

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
        feedAdapter = ArticleFeedAdapter()
        _binding?.feedRecycler?.adapter = feedAdapter

        viewModel.fetchGlobalFeed()

        viewModel.feed.observe(viewLifecycleOwner) {
            feedAdapter.submitList(it)
        }
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}