package at.fh.swengb.loecker.homeexercise2

import adapters.NoteAdapter
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity() {

	private lateinit var sharedPreferences: SharedPreferences
	private lateinit var noteAdapter: NoteAdapter
	private lateinit var db: NotesRoomDatabase

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_note_list)

		noteAdapter = adapters.NoteAdapter(
			{
				// clickListener
				val intent = Intent(this, AddNoteActivity::class.java)
				intent.putExtra(Note.EXTRA_NOTE_ID, it.id)
				startActivity(intent)
			}, {
				// longClickListener
				//Toast.makeText(this, "Note long clicked: ${it.id}", Toast.LENGTH_SHORT).show()
			}
		)

		sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
		val userName = sharedPreferences.getString("UserName",null)
		val userAge = sharedPreferences.getInt("UserAge",-1)
		val userId = sharedPreferences.getLong("UserId", -1)

		if (userId == -1L) {
			destroySession()
		} else {
			user_info.text = "Notes for ${userName}, ${userAge}"
			updateRecycler(userId)
		}
	}

	override fun onResume() {
		super.onResume()

		sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
		val userId = sharedPreferences.getLong("UserId", -1)

		if (userId == -1L) {
			destroySession()
		} else {
			updateRecycler(userId)
		}
	}

	fun updateRecycler(userId: Long) {
		db = NotesRoomDatabase.getDatabase(this)
		noteAdapter.updateList(db.noteDao.findNotesFromUser(userId))

		recycler_view.adapter = noteAdapter
		recycler_view.layoutManager = LinearLayoutManager(this)
	}

	fun destroySession() {
		sharedPreferences.edit().remove("UserName").commit()
		sharedPreferences.edit().remove("UserAge").commit()
		sharedPreferences.edit().remove("UserId").commit()

		val intent = Intent(this, MainActivity::class.java)
		startActivity(intent)
	}

	fun addNote(view: View) {
		val userId = sharedPreferences.getLong("UserId", -1)
		val intent = Intent(this, AddNoteActivity::class.java)
		intent.putExtra(User.EXTRA_USER_ID, userId)
		startActivity(intent)
	}

	fun logout(view: View) {
		destroySession()
	}
}
