package DAL

import ENT.DesafioEntity
import ENT.MascotaDesafioEntity
import ENT.MascotaEntity
import ENT.UsuarioEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AnimalDao {

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertarUsuario(usuario: UsuarioEntity)

    @Query("SELECT * FROM Usuarios Where Id = 1")
    suspend fun sacaUsuario():UsuarioEntity

    @Query("SELECT * FROM Mascotas")
    suspend fun sacaAnimales():List<MascotaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertaAnimal(amimal: MascotaEntity)

    @Query("SELECT * FROM Mascotas WHERE id == :idMascota")
    suspend fun detallesMascota(idMascota: Int): MascotaEntity

    @Query("SELECT * FROM MascotaDesafios WHERE idMascota == :idMascota")
    suspend fun desafiosMascota(idMascota: Long): List<MascotaDesafioEntity>

    @Query("SELECT * FROM Desafios WHERE id == :idDesafio")
    suspend fun sacaDesafio(idDesafio: Long): DesafioEntity

    @Query("SELECT * FROM Desafios")
    suspend fun listaDesafios(): List<DesafioEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarDesafio(desafio: DesafioEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarDesafioMascota(desafioMascota: MascotaDesafioEntity)

    @Query("SELECT id FROM Desafios WHERE nombreDesafio == :nombre")
    suspend fun sacaDesafioNombre(nombre: String): Int

}