package at.fh.swengb.loecker.homeexercise2

import adapters.NoteAdapter
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {

	//private val notes = mutableListOf<Note>()
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
		val note = Note(title, content)
		//val note = Note(title, content, 0)

		/*notes.add(note)
		noteAdapter.updateList(notes)*/

		db.noteDao.insert(note)
		noteAdapter.updateList(db.noteDao.findAll())

		val intent = Intent(this, NoteListActivity::class.java)
		startActivity(intent)
	}
}
