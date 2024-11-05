package com.example.listatareas.dal

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TareasEntity::class], version = 1)
abstract class TareasDataBase: RoomDatabase() {
    abstract fun TareasDao(): TareasDao

}