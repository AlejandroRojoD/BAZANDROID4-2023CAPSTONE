package com.example.wizelineproject.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wizelineproject.R
import com.example.wizelineproject.databinding.FragmentLoginBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable


@AndroidEntryPoint
class LoginFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        Observable.just(Firebase.auth).subscribe {
            auth = it
            if (it.currentUser != null) {
                goToMoviesListFragment()
            }
        }

    }

    private fun initViews() {
        binding.btnRegister.setOnClickListener(this)
        binding.btnLogin.setOnClickListener(this)
        binding.btnGoogle.setOnClickListener(this)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }

    private fun goToMoviesListFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_moviesListFragment)
    }

    private fun goToRegisterFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnLogin.id -> {
                validateCredentials()
            }
            binding.btnGoogle.id -> {
                initSignInWithGoogle()
            }
            binding.btnRegister.id -> {
                goToRegisterFragment()
            }
        }
    }

    private fun initSignInWithGoogle() {

    }

    private fun validateCredentials() {
        if (!binding.userField.text?.trim().isNullOrEmpty() && !binding.passwordField.text?.trim()
                .isNullOrEmpty()
        ) {
            Observable.fromCallable<Task<AuthResult>> {
                auth.signInWithEmailAndPassword(
                    binding.userField.text.toString(),
                    binding.passwordField.text.toString()
                )
            }.subscribe {
                it.addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        goToMoviesListFragment()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            requireContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

        }
    }
}