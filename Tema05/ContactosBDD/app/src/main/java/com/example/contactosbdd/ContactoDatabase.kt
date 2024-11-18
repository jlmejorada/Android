package com.example.contactosbdd

import androidx.room.Database
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [ContactoEntity::class], version = 1)
abstract class ContactoDataBase: RoomDatabase() {

    companion object {
        lateinit var coroutine: CoroutineScope
    }

    abstract fun ContactoDao(): ContactoDao

}