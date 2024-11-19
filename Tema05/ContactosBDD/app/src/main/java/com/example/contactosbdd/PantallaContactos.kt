package com.example.contactosbdd

import android.content.ContentValues.TAG
import android.util.Log
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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.booleanResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// Esta pantalla tiene un Mensaje de bienvenido al usuario con el que hemos accedido al programa, un lazycolumn
// que muestra los contactos que tenemos en la bdd y un botón que lanza un popup para añadir contactos
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
        Spacer(
            Modifier.height(20.dp)
        )
        Button(
                onClick = {
                    isDialogVisible = true
                }
        ) {
            Text(text = "Añadir Contacto")
        }
    }

}

// Lista de contactos
@Composable
fun ListaContactos(lista: List<ContactoEntity>) {
    LazyColumn {	// produce una lista de desplazamiento vertical,
        items(lista) { contacto ->
            CartaDeContacto(contacto)
        }
    }
}

// Carta de cada contacto
@Composable
fun CartaDeContacto(contacto: ContactoEntity){
    var imagen = when (contacto.imagen) {
        "pelocorto" -> R.drawable.pelocorto
        "pelolargo" -> R.drawable.pelolargo
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
                    text = contacto.telefono,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}


// Esta función saca la lista de contactos de la bdd para así usarla en el lazycolumn
suspend fun SacaContactos(): List<ContactoEntity>{
    return withContext(Dispatchers.IO){
        MainActivity.basedatos.ContactoDao().getAll();
    }
}

// Pop up que tiene unos input para recoger los datos del usuario, un boton para añadir este y otro para volver atras
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AyudaDialog(onDismiss: () -> Unit, navController: NavHostController, usuario: String?) {
    var nombre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var imagen by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    var estaAbierto by remember { mutableStateOf(false) }
    val fotos = listOf("basico", "pelocorto", "pelolargo")

    Dialog(onDismissRequest = onDismiss) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
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
            ExposedDropdownMenuBox(
                expanded = estaAbierto,
                onExpandedChange = { estaAbierto = !estaAbierto },

                ) {
                OutlinedTextField(
                    value = imagen,
                    onValueChange = { },
                    label = { Text("Selecciona una imagen") },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = estaAbierto) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )

                //logica del menu
                ExposedDropdownMenu(
                    expanded = estaAbierto,
                    onDismissRequest = { estaAbierto = false }
                ) {
                    fotos.forEach { foto ->
                        DropdownMenuItem(
                            text = { Text(foto) },
                            onClick = {
                                imagen = foto
                                estaAbierto = false
                                Log.d("foto", foto)
                            },
                            modifier = Modifier.background(Color(0xFFF2F0EF))
                        )
                    }
                }
            }
            Spacer(
                    Modifier.height(20.dp)
                    )
            Button(

                modifier = Modifier
                    .size(width = 150.dp, height = 60.dp),
                onClick = {
                    coroutineScope.launch {
                        guardaContacto(nombre, telefono, imagen, navController, usuario)
                    }
                }
            ) {
                Text("Guardar Contacto", textAlign = TextAlign.Center)
            }
            Spacer(
                Modifier.height(90.dp)
            )
            Button(
                modifier = Modifier
                    .size(width = 150.dp, height = 60.dp),
                onClick = {
                    navController.navigate("pantalla2/${usuario}")
                }
            ) {
                Text("Volver a la lista")
            }
        }
    }
}

//Funcion que guarda un contacto si los campos no están vacios
suspend fun guardaContacto (nombre: String, telefono: String, imagen: String, navController: NavHostController, usuario: String?){
    var contacto : ContactoEntity
    if (nombre!="" && telefono!=""){
        contacto = ContactoEntity(
            nombre= nombre,
            telefono = telefono,
            imagen = imagen
        )
        MainActivity.basedatos.ContactoDao().insertar(contacto);
        navController.navigate("pantalla2/${usuario}")
    }
}


