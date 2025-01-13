package com.example.rentingroom.ENT

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Clientes")
data class ClienteEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0,
    var nombre:String = "",
    var apellidos:String = "",
    var telefono:Int = 0
)