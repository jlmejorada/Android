package com.example.piedrapapeltijeras

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [JugadorEntity::class], version = 1)
abstract class JugadorDataBase: RoomDatabase() {
    abstract fun JugadorDao(): JugadorDao
}