package at.fh.swengb.loecker.homeexercise2

import adapters.NoteAdapter
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {

	private lateinit var noteAdapter: NoteAdapter
	private lateinit var db: NotesRoomDatabase
	private var userId: Long = -1

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_add_note)

		noteAdapter = adapters.NoteAdapter{}
		userId = intent.getLongExtra(User.EXTRA_USER_ID, -1)
	}

	override fun onResume() {
		super.onResume()

		if (userId == -1L) {
			this.finish()
		}
	}

	fun saveNote(view: View) {
		val title = input_note_title.text.toString()
		val content = input_note_content.text.toString()
		val note = Note(title, content, userId)

		if (!title.isEmpty() && !content.isEmpty()) {
			db = NotesRoomDatabase.getDatabase(this)
			db.noteDao.insertNote(note)
			noteAdapter.updateList(db.noteDao.findNotesFromUser(userId))

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
