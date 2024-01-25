package io.realWorld.android.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import io.realWorld.android.R
import io.realWorld.android.databinding.FragmentLoginBinding
import io.realWorld.android.databinding.FragmentSignupBinding

class SignUpFragment : Fragment() {

    private var _binding : FragmentSignupBinding?=null
    private var navController: NavController?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater,container,false)
        val navController = findNavController()
        _binding?.tvLogin?.setOnClickListener {

            val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.nav_signup, true)
            .build()
            navController.navigate(R.id.action_nav_signup_to_nav_login, null, navOptions)
        }
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}