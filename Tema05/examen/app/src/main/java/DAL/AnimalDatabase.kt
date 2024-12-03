package DAL

import ENT.DesafioEntity
import ENT.MascotaDesafioEntity
import ENT.MascotaEntity
import ENT.UsuarioEntity
import androidx.room.Database
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [UsuarioEntity::class, MascotaEntity::class, DesafioEntity::class, MascotaDesafioEntity::class ], version = 1)
abstract class AnimalDatabase: RoomDatabase() {
    abstract fun animalDao(): AnimalDao

    companion object {
        lateinit var coroutine: CoroutineScope
    }

}
