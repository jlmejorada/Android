package com.example.thetruerepaso

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun calculadoraVista(navController: NavHostController, name:String){
    var num1 by rememberSaveable { mutableStateOf("") }
    var num2 by rememberSaveable {  mutableStateOf("") }
    var res by rememberSaveable {  mutableStateOf("") }
    Column (
        Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Button(
            onClick = { navController.popBackStack() },

            ){
            Text("Back")
        }
        Text(
            text = "Hello $name!"
        )
        Spacer(
            Modifier.height(40.dp)
        )
        Text(

            text = "El resultado es: " + res
        )
        Spacer(
            Modifier.height(40.dp)
        )
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
        Spacer(
            Modifier.height(50.dp)
        )
        Row(){
            Button(
                onClick = {
                    res= suma(num1,num2).toString()
                }
            ) {
                Text("+")
            }
            Button(
                onClick = {
                    res=resta(num1,num2).toString()
                }
            ) {
                Text("-")
            }
            Button(
                onClick = {
                    res=mul(num1,num2).toString()
                }
            ) {
                Text("*")
            }
            Button(
                onClick = {
                    res=div(num1,num2).toString()
                }
            ) {
                Text("/")
            }
        }
    }
}

fun div(factor1: String, factor2: String): Double {

    val num1 = factor1.toDoubleOrNull() ?: 0.0
    val num2 = factor2.toDoubleOrNull() ?: 0.0
    val res = num1 / num2
    return res
}

fun mul(factor1: String, factor2: String): Double {
    val num1 = factor1.toDoubleOrNull() ?: 0.0
    val num2 = factor2.toDoubleOrNull() ?: 0.0
    val res = num1 * num2
    return res
}

fun resta(factor1: String, factor2: String): Double {
    val num1 = factor1.toDoubleOrNull() ?: 0.0
    val num2 = factor2.toDoubleOrNull() ?: 0.0
    val res = num1 - num2
    return res
}

fun suma(factor1: String, factor2: String): Double {
    val num1 = factor1.toDoubleOrNull() ?: 0.0
    val num2 = factor2.toDoubleOrNull() ?: 0.0
    val res = num1 + num2
    return res
}
