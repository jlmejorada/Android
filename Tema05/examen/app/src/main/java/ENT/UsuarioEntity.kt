package ENT

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Usuarios")
data class UsuarioEntity (
    @PrimaryKey(autoGenerate = true)
    var id:Long = 1,
    var nombreUsuario:String = "usuario",
    var avatar:String = "defaultusu"
)
