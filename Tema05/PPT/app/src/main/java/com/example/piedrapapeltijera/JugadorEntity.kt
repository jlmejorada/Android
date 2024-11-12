package com.example.piedrapapeltijera

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Jugadores")
data class JugadorEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var nombre:String = "",
    var partidasJugadas:Int = 0,
    var partidasGanadas:Int = 0,
    var luchasGanadas:Int = 0
)