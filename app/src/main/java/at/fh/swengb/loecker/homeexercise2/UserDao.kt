package at.fh.swengb.loecker.homeexercise2

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM User ORDER BY id")
    fun findAll(): List<Note>

    @Query("SELECT * FROM User WHERE name = :name")
    fun findUsersWithName(name: String): List<Note>

    /*@Delete
    fun clearDatabase(user: User)

    @Query("DELETE FROM User")
    fun deleteAll()*/
}