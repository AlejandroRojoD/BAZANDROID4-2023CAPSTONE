package com.example.wizelineproject.presentation.ui

import android.view.View
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.wizelineproject.BadAplication
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest {

    @Test
    fun testMyFragment() {
        // Iniciar la actividad de prueba
        val scenario = launchFragmentInContainer<LoginFragment>()
        var view = View(BadAplication.appContext)
        scenario.onFragment {
            view = it.view!!
        }

        // Verifica que la vista no sea nula
        assertNotNull(view)

    }

}