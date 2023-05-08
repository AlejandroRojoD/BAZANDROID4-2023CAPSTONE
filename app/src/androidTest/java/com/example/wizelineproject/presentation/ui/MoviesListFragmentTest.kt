package com.example.wizelineproject.presentation.ui

import android.view.View
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.wizelineproject.BadAplication
import com.example.wizelineproject.R
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MoviesListFragmentTest {

    @Test
    fun testMyRecycler() {
        val scenario = launchFragmentInContainer<MoviesListFragment>()
        var view = View(BadAplication.appContext)
        scenario.onFragment {
            view = it.view!!
        }

        // Verifica que la vista no sea nula
        assertNotNull(view)

        // Obtener el RecyclerView del fragmento
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerMovies)

        // Verificar que el RecyclerView no es nulo
        assertNotNull(recyclerView)

        // Verificar que el RecyclerView tiene al menos un elemento
        assertTrue((recyclerView.adapter?.itemCount ?: 0) > 0)

    }

}