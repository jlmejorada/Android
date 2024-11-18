package com.example.contactosbdd

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun PantallaContactos(navController: NavHostController, nombre: String?) {
    var isDialogVisible by remember { mutableStateOf(false) }
    var contactos by remember { mutableStateOf<List<ContactoEntity>>(emptyList()) }
    if (isDialogVisible) {
        AyudaDialog(onDismiss = { isDialogVisible = false }, navController, nombre)
    }
    LaunchedEffect(Unit) {
        contactos = SacaContactos()
    }
    Column (horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text = "Lista de contactos de $nombre")
        Spacer(modifier = Modifier.height(15.dp))
        ListaContactos(contactos)
        Button(
                onClick = {
                    isDialogVisible = true
                }
        ) {
            Text(text = "Añadir Contacto")
        }
    }

}

@Composable
fun ListaContactos(lista: List<ContactoEntity>) {
    LazyColumn {	// produce una lista de desplazamiento vertical,
        items(lista) { contacto ->
            CartaDeContacto(contacto)
        }
    }
}

@Composable
fun CartaDeContacto(contacto: ContactoEntity) {
    var imagen = when (contacto.imagen) {
        "corto" -> R.drawable.pelocorto
        "largo" -> R.drawable.pelolargo
        else -> R.drawable.basico
    }

    Card(Modifier.fillMaxWidth()) {
        Row {
            Column {
                Image(
                    painter = painterResource(imagen),
                    contentDescription = "Foto contacto",
                    Modifier.height(100.dp)
                )
            }
            Column {
                Text(
                    text = contacto.nombre,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = contacto.telefono
                )
                Text(
                    text = contacto.telefono,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}



suspend fun SacaContactos(): List<ContactoEntity>{
    return withContext(Dispatchers.IO){
        MainActivity.basedatos.ContactoDao().getAll();
    }
}

@Composable
fun AyudaDialog(onDismiss: () -> Unit, navController: NavHostController, usuario: String?) {
    var contacto by remember { mutableStateOf<ContactoEntity>(value = ContactoEntity()) }
    var nombre by remember { mutableStateOf(TextFieldValue("")) }
    var telefono by remember { mutableStateOf(TextFieldValue("")) }
    var imagen by remember { mutableStateOf(TextFieldValue("")) }
    Dialog(onDismissRequest = onDismiss) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
                .background(Color.White)
        ){
            Text(
                text = "Contacto a añadir:",
                fontSize = 30.sp
            )
            Spacer(
                Modifier.height(90.dp)
            )
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre= it },
                label = { Text("Nombre") },
                placeholder = { Text("Nombre") },
                singleLine = true,
            )
            OutlinedTextField(
                value = telefono,
                onValueChange = { telefono= it },
                label = { Text("Telefono") },
                placeholder = { Text("Telefono") },
                singleLine = true,
            )
            OutlinedTextField(
                value = imagen,
                onValueChange = { imagen= it },
                label = { Text("Imagen") },
                placeholder = { Text("Imagen") },
                singleLine = true,
            )
            Button(
                modifier = Modifier.size(width = 150.dp, height = 60.dp),
                onClick = {
//                    LaunchedEffect(Unit) {
//                        contactos = SacaContactos()
//                    }
//                    contacto=ujasj

                }
            ) {
                Text("Volver a la lista")
            }
            Spacer(
                Modifier.height(90.dp)
            )
            Button(
                modifier = Modifier.size(width = 150.dp, height = 60.dp),
                onClick = {
                    navController.navigate("pantalla2/${usuario}")
                }
            ) {
                Text("Volver a la lista")
            }
        }
    }
}


