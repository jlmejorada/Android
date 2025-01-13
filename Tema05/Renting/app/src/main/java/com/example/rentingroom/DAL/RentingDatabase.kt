package com.example.rentingroom.DAL

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rentingroom.ENT.AlquilerEntity
import com.example.rentingroom.ENT.ClienteEntity
import com.example.rentingroom.ENT.ConfiguracionEntity
import kotlinx.coroutines.CoroutineScope

@Database(entities = [ClienteEntity::class, ConfiguracionEntity::class, AlquilerEntity::class], version = 1)
abstract class RentingDatabase: RoomDatabase() {

    abstract fun rentingDao(): RentingDao

    companion object {
        lateinit var coroutine: CoroutineScope
    }


}