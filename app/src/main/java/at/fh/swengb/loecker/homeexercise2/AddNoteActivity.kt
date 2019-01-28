package at.fh.swengb.loecker.homeexercise2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {

	private lateinit var db: NotesRoomDatabase
	private var userId: Long = -1
	private var noteId: Long = -1

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_add_note)

		userId = intent.getLongExtra(User.EXTRA_USER_ID, -1)
		noteId = intent.getLongExtra(Note.EXTRA_NOTE_ID, -1)

		if (noteId != -1L) {
			prefill()
		} else {
			// nothing
		}
	}

	override fun onResume() {
		super.onResume()

		if (userId == -1L && noteId == -1L) {
			this.finish()
		} else if (userId != -1L && noteId != -1L) {
			prefill()
		} else {
			// nothing - just the super.onResume()
		}
	}

	fun prefill() {
		db = NotesRoomDatabase.getDatabase(this)
		val note = db.noteDao.findNoteWithId(noteId)
		input_note_title.setText(note.title)
		input_note_content.setText(note.content)
	}

	fun saveNote(view: View) {
		val title = input_note_title.text.toString()
		val content = input_note_content.text.toString()

		if (!title.isEmpty() && !content.isEmpty()) {
			db = NotesRoomDatabase.getDatabase(this)

			if (noteId != -1L) {
				val note = db.noteDao.findNoteWithId(noteId)
				note.title = title
				note.content = content

				db.noteDao.updateNote(note)
			} else {
				val note = Note(title, content, userId)
				db.noteDao.insertNote(note)
			}

			this.finish()
		} else {
			if (title.isEmpty()) {
				Toast.makeText(this, R.string.add_note_title_missing, Toast.LENGTH_SHORT).show()
			} else {
				Toast.makeText(this, R.string.add_note_content_missing, Toast.LENGTH_SHORT).show()
			}
		}
	}

	fun shareNote(view: View) {
		val content = input_note_content.text.toString()

		if (content.isEmpty()) {
			Toast.makeText(this, R.string.add_note_content_missing, Toast.LENGTH_SHORT).show()
		} else {
			val intent = Intent(Intent.ACTION_SEND)
			intent.putExtra(Intent.EXTRA_TEXT, content)
			intent.type = "text/plain"
			val chooserIntent = Intent.createChooser(intent, getString(R.string.add_note_share_title))
			startActivity(chooserIntent)
		}
	}
}
