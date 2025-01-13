package com.example.rentingroom.DAL

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rentingroom.ENT.ConfiguracionEntity

@Dao
interface RentingDao {

    //INSERTS
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarConfiguracion(configuracion: ConfiguracionEntity)

}