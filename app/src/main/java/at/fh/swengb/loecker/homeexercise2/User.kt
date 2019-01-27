package at.fh.swengb.loecker.homeexercise2

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "user")
class User (val name: String, val age: Int) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    companion object {
        val EXTRA_USER_ID = "USER_ID_EXTRA"
    }
}