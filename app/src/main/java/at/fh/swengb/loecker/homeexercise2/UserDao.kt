package at.fh.swengb.loecker.homeexercise2

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM user WHERE name = :name")
    fun findUserWithName(name: String): User // can do this because each user has a unique name

    @Query("SELECT * FROM user WHERE id = :id")
    fun findUserWithId(id: Long): User

    /*@Query("SELECT * FROM user ORDER BY id")
    fun findAllUser(): List<User>

    @Query("DELETE FROM user")
    fun deleteAll()*/
}