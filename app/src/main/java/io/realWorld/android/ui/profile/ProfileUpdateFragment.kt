package io.realWorld.android.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import io.realWorld.android.AuthViewModel
import io.realWorld.android.databinding.FragmentProfileUpdateBinding

class ProfileUpdateFragment:Fragment() {
    private var _binding : FragmentProfileUpdateBinding ?= null
    private val authViewModel by activityViewModels<AuthViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileUpdateBinding.inflate(inflater,container,false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authViewModel.user.observe(viewLifecycleOwner){
            _binding?.apply {
                etImgUrl.setText(it?.image ?: "")
                etUsername.setText(it?.username ?: "")
                etBio.setText(it?.bio ?: "")
                etEmail.setText(it?.email ?: "")
            }
        }
        _binding?.apply {
            btnUpdate.setOnClickListener {
                authViewModel.update(
                    bio = etBio.text.toString().takeIf{ it.isNotBlank() },
                    image = etImgUrl.text.toString().takeIf{ it.isNotBlank() },
                    username = etUsername.text.toString().takeIf{ it.isNotBlank() },
                    password = etPassword.text.toString(),
                    email = etEmail.text.toString()
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}