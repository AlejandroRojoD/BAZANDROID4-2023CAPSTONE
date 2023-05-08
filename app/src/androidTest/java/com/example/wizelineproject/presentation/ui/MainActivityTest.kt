package com.example.wizelineproject.presentation.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    fun testActivity() {
        // Iniciar la actividad
        val scenario = ActivityScenario.launch(MainActivity::class.java)

        // Obtener la instancia de la actividad
        scenario.onActivity { activity ->
            // Verificar que la actividad no es nula
            assertNotNull(activity)
        }

        // Cerrar la actividad
        scenario.close()
    }

}