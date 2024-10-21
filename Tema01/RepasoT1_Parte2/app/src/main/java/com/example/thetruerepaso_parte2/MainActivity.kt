package com.example.thetruerepaso_parte2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MaterialTheme{
                Column(
                    modifier=Modifier,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "calc"
                    ) {
                        composable(route="calc") { PantallaCalculadora(navController, Modifier)  }
                        composable(route="suma/{num1}/{num2}") { b-> PantallaSuma(navController, Modifier,  b.arguments?.getString("num1")?:"0.0", b.arguments?.getString("num2")?:"0.0")  }
                        composable(route="resta/{num1}/{num2}") { b-> PantallaResta(navController, Modifier,  b.arguments?.getString("num1")?:"0.0", b.arguments?.getString("num2")?:"0.0")  }
                        composable(route="multiplicacion/{num1}/{num2}") { b-> PantallaMul(navController, Modifier, b.arguments?.getString("num1")?:"0.0", b.arguments?.getString("num2")?:"0.0")  }
                        composable(route="division/{num1}/{num2}") { b-> PantallaDiv(navController, Modifier, b.arguments?.getString("num1")?:"0.0", b.arguments?.getString("num2")?:"0.0") }
                    }
                }
            }

        }
    }
}
