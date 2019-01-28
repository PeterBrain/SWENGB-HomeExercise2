package at.fh.swengb.loecker.homeexercise2

import android.arch.persistence.room.*

@Dao
interface NoteDao {
	@Insert
	fun insertNote(note: Note)

	@Update
	fun updateNote(note: Note)

	@Delete
	fun deleteNote(note: Note)

	@Query("SELECT * FROM notes ORDER BY id")
	fun findAllNotes(): List<Note>

	@Query("SELECT * FROM notes WHERE userId = :userId")
	fun findNotesFromUser(userId: Long): List<Note>

	@Query("SELECT * FROM notes WHERE id = :id")
	fun findNoteWithId(id: Long): Note

	/*@Query("SELECT * FROM notes WHERE title = :title")
	fun findNotesWithTitle(title: String): List<Note>

	@Query("DELETE FROM notes")
	fun deleteAll()*/
}
