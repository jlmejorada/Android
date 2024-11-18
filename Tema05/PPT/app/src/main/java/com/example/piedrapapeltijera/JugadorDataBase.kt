package com.example.piedrapapeltijera

import androidx.room.Database
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [JugadorEntity::class], version = 1)
abstract class JugadorDataBase: RoomDatabase() {

    companion object {
        lateinit var coroutine: CoroutineScope
    }

    abstract fun JugadorDao(): JugadorDao

}