package com.example.thetruerepaso_parte2

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Botonera(navController: NavController, pantallaActual: String, num1: String, num2: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,

        ) {
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            enabled = pantallaActual != "suma",
            onClick = {
                navController.navigate("suma/$num1/$num2")
            }
        ) {
            Text("+")
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            enabled = pantallaActual != "resta",
            onClick = {
                navController.navigate("resta/$num1/$num2")
            }) {
            Text("-")
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            enabled = pantallaActual != "multiplicacion",
            onClick = {
                navController.navigate("multiplicacion/$num1/$num2")
            }) {
            Text("*")
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            enabled = pantallaActual != "division",
            onClick = {
                navController.navigate("division/$num1/$num2")
            }) {
            Text("/")
        }
        Spacer(modifier = Modifier.width(16.dp))
    }
}