package at.fh.swengb.loecker.homeexercise2

import adapters.NoteAdapter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {

	private val noteAdapter = NoteAdapter()
	lateinit var db: NotesRoomDatabase

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_add_note)

		db = NotesRoomDatabase.getDatabase(this)
		noteAdapter.updateList(db.noteDao.findAll())
	}

	fun saveNote(view: View) {
		val title = input_note_title.text.toString()
		val content = input_note_content.text.toString()
		val note = Note(0, title, content)

		db.noteDao.insert(note)
		noteAdapter.updateList(db.noteDao.findAll())

		this.finish() // close activity
	}
}
