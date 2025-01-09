package com.example.aplicacionsonidos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Animal")
data class AnimalEntity (
    @PrimaryKey()
    var id:Long = 0,
    var nombre:String = ""
)