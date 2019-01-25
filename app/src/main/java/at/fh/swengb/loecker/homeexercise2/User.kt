package at.fh.swengb.loecker.homeexercise2

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class User (val name: String, var age: Int) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}