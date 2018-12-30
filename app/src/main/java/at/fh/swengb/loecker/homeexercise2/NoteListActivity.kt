package at.fh.swengb.loecker.homeexercise2

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_note_list)

		val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)

		val user_name = sharedPreferences.getString("UserName", null)
		val user_age = sharedPreferences.getInt("UserAge", -1)

		user_info.text = "Notes for ${user_name}, ${user_age}"
	}

	fun addNote(view: View) {
		val intent = Intent(this, AddNoteActivity::class.java)
		startActivity(intent)
	}
}
