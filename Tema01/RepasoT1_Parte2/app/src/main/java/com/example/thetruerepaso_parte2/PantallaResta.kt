package com.example.thetruerepaso_parte2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun PantallaResta (navController: NavController, modifier: Modifier, car1: String, car2: String){
    var res by rememberSaveable {  mutableStateOf(
        car1.toDouble() - car2.toDouble()
    ) }
    Column {
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ){
            Button(
                onClick = { navController.navigate("calc")},

                ){
                Text("Back")

            }
        }
        Spacer(
            modifier = Modifier.weight(1f)
        )
        Text(

            text = "El resultado es: " + res
        )
        Spacer(
            modifier = Modifier.weight(1f)
        )
        Botonera(navController, "resta", car1, car2)
    }
}