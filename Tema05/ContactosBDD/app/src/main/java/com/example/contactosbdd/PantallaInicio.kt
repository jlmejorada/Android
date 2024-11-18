package com.example.contactosbdd

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun PantallaInicio(navController: NavHostController) {
    val image = painterResource(R.drawable.inicio)
    var nombre by remember { mutableStateOf(TextFieldValue("")) }
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
            value = nombre,
            onValueChange = { nombre= it },
            label = { Text("Usuario") },
            placeholder = { Text("Usuario") },
            singleLine = true,
        )
        Spacer(
            Modifier.height(50.dp)
        )
        Button (
            onClick = {
                coroutineScope.launch {
                    navController.navigate("pantalla2/${nombre.text}")
                }
            },
            Modifier
                .width(140.dp)
                .height(42.dp)
        ){
            Text("Entrar")
        }
    }


}