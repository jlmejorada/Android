package com.example.piedrapapeltijera

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun PantallaInicio(navController: NavHostController) {
    val image = painterResource(R.drawable.ppt)
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        Image (
            painter = image,
            null
        )
        Spacer(
            Modifier.height(50.dp)
        )
        Button(
            modifier = Modifier.size(width = 100.dp, height = 60.dp),
            onClick = {
                navController.navigate("pantalla2")
            }
        ) {
            Text("JUGAR")
        }
    }
}