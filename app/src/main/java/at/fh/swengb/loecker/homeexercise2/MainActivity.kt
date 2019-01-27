package at.fh.swengb.loecker.homeexercise2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	private lateinit var sharedPreferences: SharedPreferences
	private lateinit var db: NotesRoomDatabase

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
	}

	fun save_button(view: View) {
		val userName: String = input_name.text.toString()
		var userAge: String = input_age.text.toString()

		if (userName.isEmpty()) {
			Toast.makeText(this, "Please fill in your name!", Toast.LENGTH_SHORT).show()
		} else if(userAge.isEmpty()) {
			Toast.makeText(this, "Please fill in your age!", Toast.LENGTH_SHORT).show()
		} else {
			sharedPreferences.edit().putString("UserName", userName).apply()
			sharedPreferences.edit().putInt("UserAge", userAge.toInt()).apply()

			db = NotesRoomDatabase.getDatabase(this)
			var user = db.userDao.findUserWithName(userName)

			if (user.name.isEmpty()) {
				db.userDao.insertUser(User(userName, userAge.toInt()))
				user = db.userDao.findUserWithName(userName)
			}

			sharedPreferences.edit().putLong("UserId", user.id).apply()

			input_name.text.clear()
			input_age.text.clear()

			toNoteListActivity()
		}
	}

	override fun onResume() {
		super.onResume()

		val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
		val userId = sharedPreferences.getLong("UserId",-1)

		if (userId != -1L) {
			toNoteListActivity()
		}
	}

	fun toNoteListActivity() {
		val intent = Intent(this, NoteListActivity::class.java)
		startActivity(intent)

		this.finish()
	}
}
