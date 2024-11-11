package com.example.piedrapapeltijeras

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch


@Composable
fun PantallaInicio(navController: NavHostController) {
    val image = painterResource(R.drawable.ppt)
    val nombre = remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        Image (
            painter = image,
            null
        )
        OutlinedTextField(
            value = nombre.value,
            onValueChange = { newText ->
                nombre.value = newText
            },
            label = { Text("Ingresa tu nombre") },
            placeholder = { Text("Nombre") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Spacer(
            Modifier.height(50.dp)
        )
        Button(
            modifier = Modifier.size(width = 100.dp, height = 60.dp),
            onClick = {
                coroutineScope.launch {

                    //if (nombre != JugadorDataBase.){
                        JugadorDao.in
                    //}

                }
                navController.navigate("pantalla2")
            }
        ) {
            Text("JUGAR")
        }
    }
}