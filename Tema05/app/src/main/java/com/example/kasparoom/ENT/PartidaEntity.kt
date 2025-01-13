package com.example.kasparoom.ENT

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.type.DateTime

@Entity(tableName = "Partidas")
data class PartidaEntity (
    @PrimaryKey(autoGenerate = true)
    var id:Long=0,
    var idJugador1:Long = 1,
    var idJugador2:Long = 1,
    var resultado:Int = 0,
    var fecha:String
)