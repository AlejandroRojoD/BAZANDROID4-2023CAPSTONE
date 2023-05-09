package com.example.wizelineproject.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wizelineproject.R
import com.example.wizelineproject.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        binding.btnRegister.setOnClickListener {
            if (validateFields()) {
                createUserWithFirebase()
            }
        }
    }

    private fun createUserWithFirebase() {
        auth.createUserWithEmailAndPassword(
            binding.userField.text.toString(),
            binding.passwordField.text.toString()
        )
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    goToLoginFragment()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        requireContext(), "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun validateFields(): Boolean =
        !binding.passwordField.text?.trim().isNullOrEmpty() && !binding.userField.text?.trim()
            .isNullOrEmpty()

    private fun goToLoginFragment() {
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }


    companion object {
        val TAG = RegisterFragment::class.java.canonicalName!!

        @JvmStatic
        fun newInstance() = RegisterFragment()
    }
}