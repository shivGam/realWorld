package io.realWorld.android.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.realWorld.android.R
import io.realWorld.android.databinding.FragmentArticleBinding
import io.realWorld.android.databinding.FragmentCrBinding
import io.realWorld.android.ui.extensions.loadImage

class CreateArticleFragment:Fragment() {

    private var _binding: FragmentCrBinding?= null
    private lateinit var articleViewModel : ArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCrBinding.inflate(inflater,container,false)
        articleViewModel = ViewModelProvider(this)[ArticleViewModel::class.java]

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            _binding?.apply {
                btnCreate.setOnClickListener {
                    articleViewModel.createArticle(
                        title = etTitle.text.toString().takeIf { it.isNotBlank() },
                        description = etAbout.text.toString().takeIf { it.isNotBlank() },
                        body = etBody.text.toString().takeIf { it.isNotBlank() },
                        tagList = etTag.text.toString().split("\\s".toRegex())
                    )
                    Toast.makeText(requireContext(),"Article Published",Toast.LENGTH_SHORT).show()
                }
            }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}