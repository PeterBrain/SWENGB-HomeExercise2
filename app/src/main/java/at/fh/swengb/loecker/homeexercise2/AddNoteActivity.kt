package at.fh.swengb.loecker.homeexercise2

import adapters.NoteAdapter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {

	private lateinit var noteAdapter: NoteAdapter
	private lateinit var db: NotesRoomDatabase
	private lateinit var user: User
	private var userId: Long = 0

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_add_note)

		userId = intent.getLongExtra(User.EXTRA_USER_ID, 0)

		db = NotesRoomDatabase.getDatabase(this)
		user = db.userDao.findUserWithId(userId)
	}

	fun saveNote(view: View) {
		val title = input_note_title.text.toString()
		val content = input_note_content.text.toString()
		val note = Note(title, content, userId)

		if (!title.isEmpty() && !content.isEmpty()) {
			db.noteDao.insertNote(note)
			noteAdapter.updateList(db.noteDao.findNotesFromUser(userId))

			this.finish()
		} else {
			if (title.isEmpty()) {
				Toast.makeText(this, "Please fill in a title!", Toast.LENGTH_SHORT).show()
			} else {
				Toast.makeText(this, "Please fill in some content!", Toast.LENGTH_SHORT).show()
			}
		}
	}

	fun shareNote(view: View) {

	}
}
