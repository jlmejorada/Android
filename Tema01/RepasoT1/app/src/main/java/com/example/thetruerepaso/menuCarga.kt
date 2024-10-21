package com.example.thetruerepaso

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun pantallaDeCarga(navController: NavHostController) {
    var nombre by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable {  mutableStateOf("") }
    Column(
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            Modifier
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Usuario",
                modifier = Modifier
                    .width(100.dp)
                    .padding(end=20.dp),
                textAlign = TextAlign.End
            )
            TextField(
                value=nombre,
                modifier = Modifier
                    .width(200.dp),
                onValueChange = {nombre=it}
            )
        }
        Row(
            Modifier
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Contraseña",
                Modifier
                    .width(100.dp)
                    .padding(end=20.dp),
                textAlign = TextAlign.End
            )
            TextField(
                value=password,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .width(200.dp),
                onValueChange = {password=it}
            )
        }
        Button(
            onClick = {
                if (nombre=="pedro" && password=="perro"){
                    (navController.navigate("calculadora/$nombre"))
                }

            }
        ) {
            Text("Entrar")
        }
    }
}