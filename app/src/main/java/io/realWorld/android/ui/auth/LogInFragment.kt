package io.realWorld.android.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import io.realWorld.android.AuthViewModel
import io.realWorld.android.R
import io.realWorld.android.databinding.FragmentLoginBinding

class LogInFragment : Fragment() {

    private var _binding : FragmentLoginBinding?=null
    private var navController: NavController?=null
    val authViewModel : AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        navController?.navigate(R.id.nav_login)

        val navController = findNavController()
        _binding?.tvSignUp?.setOnClickListener {
            navController.navigate(R.id.action_nav_login_to_nav_signup)
        }
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.apply {
            btnLogin.setOnClickListener {
                authViewModel.login(
                    etEmail.text.toString(),
                    etPassword.text.toString()
                )
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}