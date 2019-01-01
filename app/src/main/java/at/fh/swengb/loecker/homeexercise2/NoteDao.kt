package at.fh.swengb.loecker.homeexercise2

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface NoteDao {
	@Insert
	fun insert(note: Note)

	@Query("SELECT * FROM Note ORDER BY title")
	fun findAll(): List<Note>

	@Query("SELECT * FROM Note WHERE title = :uTitle")
	fun findStudentsInSemester(uTitle: Int): List<Note>
}
