package com.example.kasparoom.DAL

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kasparoom.ENT.JugadorEntity
import com.example.kasparoom.ENT.PartidaEntity
import kotlinx.coroutines.CoroutineScope

@Database(entities = [JugadorEntity::class, PartidaEntity::class ], version = 1)
abstract class KaspaDatabase: RoomDatabase() {
    abstract fun kaspaDao(): KaspaDao

    companion object {
        lateinit var coroutine: CoroutineScope
    }

}