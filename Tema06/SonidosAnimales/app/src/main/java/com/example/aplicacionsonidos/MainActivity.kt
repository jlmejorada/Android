package com.example.aplicacionsonidos

import DAL.AnimalDatabase
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.aplicacionsonidos.ui.theme.AplicacionSonidosTheme
import kotlinx.coroutines.CoroutineScope

class MainActivity : ComponentActivity() {
    companion object {
        lateinit var basedatos: AnimalDatabase
        lateinit var coroutine: CoroutineScope
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            basedatos = Room.databaseBuilder(
                this, AnimalDatabase::class.java, "animal-db"
            ).build()

            coroutine = rememberCoroutineScope()

            AplicacionSonidosTheme {
                PrincipalView()
            }
        }
    }
}
