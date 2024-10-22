package com.example.examenloteria

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PantallaEleccion(navController: NavController, modifier: Modifier = Modifier){
    var num by rememberSaveable{ mutableStateOf("") }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        Text(text="Elige un número para tu apuesta", fontSize = 25.sp)
        Spacer(
            Modifier.height(30.dp)
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                modifier = Modifier.size(width = 80.dp, height = 60.dp),
                onClick = {
                    num = "1"
                    navController.navigate("pantalla2/$num")
                }
            ) {
                Text("1")
            }
            Button(
                modifier = Modifier.size(width = 80.dp, height = 60.dp),
                onClick = {
                    num = "2"
                    navController.navigate("pantalla2/$num")
                }
            ) {
                Text("2")
            }
            Button(
                modifier = Modifier.size(width = 80.dp, height = 60.dp),
                onClick = {
                    num = "3"
                    navController.navigate("pantalla2/$num")
                }
            ) {
                Text("3")
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ){
            Button(
                modifier = Modifier.size(width = 80.dp, height = 60.dp),
                onClick = {
                    num = "4"
                    navController.navigate("pantalla2/$num")
                }
            ) {
                Text("4")
            }
            Button(
                modifier = Modifier.size(width = 80.dp, height = 60.dp),
                onClick = {
                    num = "5"
                    navController.navigate("pantalla2/$num")
                }
            ) {
                Text("5")
            }
            Button(
                modifier = Modifier.size(width = 80.dp, height = 60.dp),
                onClick = {
                    num = "6"
                    navController.navigate("pantalla2/$num")
                }
            ) {
                Text("6")
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ){
            Button(
                modifier = Modifier.size(width = 80.dp, height = 60.dp),
                onClick = {
                    num = "7"
                    navController.navigate("pantalla2/$num")
                }
            ) {
                Text("7")
            }
            Button(
                modifier = Modifier.size(width = 80.dp, height = 60.dp),
                onClick = {
                    num = "8"
                    navController.navigate("pantalla2/$num")
                }
            ) {
                Text("8")
            }
            Button(
                modifier = Modifier.size(width = 80.dp, height = 60.dp),
                onClick = {
                    num = "9"
                    navController.navigate("pantalla2/$num")
                }
            ) {
                Text("9")
            }
        }
    }
}