package com.example.cinemaroom.ENT

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Clientes")
data class ClienteEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0,
    var salaElegida:Int = 0,
    var palomitas:Int = 0
)