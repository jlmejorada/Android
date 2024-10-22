package com.example.examenloteria

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PantallaApuesta(navController: NavController, modifier: Modifier = Modifier, num: String?) {
    val saldoActual by rememberSaveable{ mutableIntStateOf(10) }
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = ("Saldo actual del jugador: $saldoActual"), fontSize = 25.sp)
        Text(text = ("El número elegido por el jugador es: $num"), fontSize = 20.sp)
        Spacer(Modifier.height(20.dp))
        Row (){
            Text(text="Apuesta")

        }
    }
}