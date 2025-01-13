package com.example.kasparoom.ENT

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Jugadores")
data class JugadorEntity (
    @PrimaryKey(autoGenerate = true)
    var id:Long=0,
    var nombre:String = "nombre",
    var puntos:Int = 0,
    var fecha:String
)