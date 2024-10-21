package com.example.thetruerepaso_parte2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PantallaCalculadora (navController: NavController, modifier: Modifier){
    var num1 by rememberSaveable { mutableStateOf("") }
    var num2 by rememberSaveable {  mutableStateOf("") }

    Column {
        Spacer(
            modifier = Modifier.weight(1f)
        )
        Column (
            Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text(
                text = "Num1",
                modifier = Modifier
                    .width(100.dp)
                    .padding(10.dp),
                textAlign = TextAlign.Center
            )
            TextField(
                value=num1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .width(200.dp),
                onValueChange = {num1=it}
            )
            Text(
                text = "Num2",
                Modifier
                    .width(100.dp)
                    .padding(10.dp),
                textAlign = TextAlign.Center
            )
            TextField(
                value=num2,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .width(200.dp),
                onValueChange = {num2=it}
            )
        }
        Spacer(
            modifier = Modifier.weight(1f)
        )
        Botonera(navController,"calc", num1, num2)
    }

}