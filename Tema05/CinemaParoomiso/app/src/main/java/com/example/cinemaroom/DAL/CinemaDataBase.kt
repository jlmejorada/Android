package com.example.cinemaroom.DAL

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cinemaroom.ENT.ClienteEntity
import com.example.cinemaroom.ENT.ConfiguracionEntity
import kotlinx.coroutines.CoroutineScope

@Database(entities = [ClienteEntity::class, ConfiguracionEntity::class], version = 1)
abstract class CinemaDataBase: RoomDatabase() {

    abstract fun cinemaDao(): CinemaDao

    companion object {
        lateinit var coroutine: CoroutineScope
    }


}