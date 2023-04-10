package com.example.wizelineproject.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wizelineproject.R
import com.example.wizelineproject.databinding.ActivitySignInBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    val signInResult = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) {
//        it.idpResponse.idpToken
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    public override fun onStart() {
        super.onStart()
        if (Firebase.auth.currentUser == null) {
            val signInIntent =
                AuthUI.getInstance().createSignInIntentBuilder().setLogo(R.mipmap.ic_launcher)
                    .setAvailableProviders(
                        listOf(
                            AuthUI.IdpConfig.EmailBuilder().build(),
                            AuthUI.IdpConfig.GoogleBuilder().build(),
                        )
                    ).build()
            signInResult.launch(signInIntent)
        } else goToMainActivity()
    }

    private fun goToMainActivity() {

    }

    companion object {
        private const val TAG = "SignInActivity"
    }
}