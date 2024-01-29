package io.realWorld.android.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.tabs.TabLayout
import io.realWorld.android.AuthViewModel
import io.realWorld.android.R
import io.realWorld.android.databinding.FragmentHomeBinding
import io.realworld.api.models.entities.User

class HomeFragment: Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private var navController: NavController? = null
    private lateinit var authViewModel: AuthViewModel
    var showFragment = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel = ViewModelProvider(requireActivity())[(AuthViewModel::class.java)]
        navController = _binding?.let { Navigation.findNavController(it.root.findViewById(R.id.homeFragmentNavHost)) }
        _binding?.homeTabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        navController?.navigate(R.id.gotoGlobalFragment)
                    }
                    1 -> {
                        authViewModel.user.observe(this@HomeFragment) { user ->
                            showFragment=true
                        }

                        if(showFragment) {
                            navController?.navigate(R.id.gotoMyFeedFragment)
                        }
                        else
                        {
                            navController?.navigate(R.id.gotoLoginFragment2)
                        }
                    }
                    else -> throw IllegalArgumentException("Invalid position")
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}