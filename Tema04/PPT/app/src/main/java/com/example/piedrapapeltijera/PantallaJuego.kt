package com.example.piedrapapeltijera

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController

@Composable
fun PantallaJuego(navController: NavHostController) {
    var ctx = LocalContext.current
    var finPartida = ""
    var res by remember { mutableStateOf("") }
    var puntJ1 by remember { mutableIntStateOf(0) }
    var puntPC by remember { mutableIntStateOf(0) }
    var elecJ1 by remember { mutableIntStateOf(0) }
    var elecJPC by remember { mutableIntStateOf(0) }
    var isDialogVisible by remember { mutableStateOf(false) }
    val piedra = painterResource(R.drawable.stone)
    val papel = painterResource(R.drawable.paper)
    val tijera = painterResource(R.drawable.shears)
    val incognita = painterResource(R.drawable.que)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "$puntJ1 VS $puntPC",
            fontSize = 30.sp
        )
        Spacer(
            Modifier.height(140.dp)
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(15.dp)
            ) {
                Image(
                    painter = when (elecJ1) {
                        1 -> piedra
                        2 -> papel
                        3 -> tijera
                        else -> incognita
                    },
                    contentDescription = null,
                    modifier = Modifier
                        .width(120.dp)
                        .height(120.dp)
                )
                Text(text = "J1")
            }
            if (isDialogVisible) {
                AyudaDialog(onDismiss = { isDialogVisible = false }, res, navController)
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(15.dp)
            ) {
                Image(
                    painter = when (elecJPC) {
                        1 -> piedra
                        2 -> papel
                        3 -> tijera
                        else -> incognita
                    },
                    contentDescription = null,
                    modifier = Modifier
                        .width(120.dp)
                        .height(120.dp)
                )
                Text(text = "PC")
            }
        }
        Spacer(
            Modifier.height(90.dp)
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = piedra,
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .padding(10.dp)
                    .clickable {
                        elecJ1 = 1
                        elecJPC = CalculaPc()
                        finPartida = sacaGanador(elecJ1, elecJPC)
                        if (finPartida == "Has ganado"){
                            puntJ1++
                        } else if (finPartida == "Gana la máquina"){
                            puntPC++
                        }
                        if (puntJ1==3 ){
                            res = "Has ganado!"
                            isDialogVisible = true
                        } else if (puntPC==3){
                            res = "Has perdido!"
                            isDialogVisible = true
                        }
                        Toast.makeText(ctx, finPartida, Toast.LENGTH_SHORT).show()
                        Thread.sleep(1000)
                    }
            )
            Image(
                painter = papel,
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .padding(10.dp)
                    .clickable {
                        elecJ1 = 2
                        elecJPC = CalculaPc()
                        finPartida = sacaGanador(elecJ1, elecJPC)
                        if (finPartida == "Has ganado"){
                            puntJ1++
                        } else if (finPartida == "Gana la máquina"){
                            puntPC++
                        }
                        if (puntJ1==3 ){
                            res = "Has ganado!"
                            isDialogVisible = true
                        } else if (puntPC==3){
                            res = "Has perdido!"
                            isDialogVisible = true
                        }
                        Toast.makeText(ctx, finPartida, Toast.LENGTH_SHORT).show()
                        Thread.sleep(1000)
                    }
            )
            Image(
                painter = tijera,
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .padding(10.dp)
                    .clickable {
                        elecJ1 = 3
                        elecJPC = CalculaPc()
                        finPartida = sacaGanador(elecJ1, elecJPC)
                        if (finPartida == "Has ganado"){
                            puntJ1++
                        } else if (finPartida == "Gana la máquina"){
                            puntPC++
                        }
                        if (puntJ1==3 ){
                            res = "Has ganado!"
                            isDialogVisible = true
                        } else if (puntPC==3){
                            res = "Has perdido!"
                            isDialogVisible = true
                        }
                        Toast.makeText(ctx, finPartida, Toast.LENGTH_SHORT).show()
                        Thread.sleep(1000)
                    }
            )
        }
    }
}

fun sacaGanador(elecJ1: Int, elecJPC: Int): String{
    var res = ""
    if (elecJ1 == 1 && elecJPC == 1){
        res="Empate"
    } else if (elecJ1 == 1 && elecJPC == 2) {
        res = "Gana la máquina"
    } else if (elecJ1 == 1 && elecJPC == 3) {
        res = "Has ganado"
    } else if (elecJ1 == 2 && elecJPC == 1) {
        res = "Has ganado"
    } else if (elecJ1 == 2 && elecJPC == 2) {
        res = "Empate"
    } else if (elecJ1 == 2 && elecJPC == 3) {
        res = "Gana la máquina"
    } else if (elecJ1 == 3 && elecJPC == 1) {
        res = "Gana la máquina"
    } else if (elecJ1 == 3 && elecJPC == 2) {
        res = "Has ganado"
    } else if (elecJ1 == 3 && elecJPC == 3) {
        res = "Empate"
    }
    return res
}

fun CalculaPc(): Int {
    val random1 = (1..3).shuffled().last()
    return random1
}

@Composable
fun AyudaDialog(onDismiss: () -> Unit, res: String, navController: NavHostController) {
    Dialog(onDismissRequest = onDismiss) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
                .background(Color.White)
        ){
            Text(
                text = res,
                fontSize = 30.sp
            )
            Spacer(
                Modifier.height(90.dp)
            )
            Button(
                modifier = Modifier.size(width = 150.dp, height = 60.dp),
                onClick = {
                    navController.navigate("pantalla1")
                }
            ) {
                Text("Volver al Inicio")
            }
        }
    }
}