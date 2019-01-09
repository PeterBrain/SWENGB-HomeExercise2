package at.fh.swengb.loecker.homeexercise2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	lateinit var sharedPreferences: SharedPreferences

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
	}

	fun save_button(view: View) {
		val username: String = input_name.text.toString()
		var userage: String = input_age.text.toString()

		if (userage == "") {
			userage = "-1"
		}

		sharedPreferences.edit().putString("UserName", username).apply()
		sharedPreferences.edit().putInt("UserAge", userage.toInt()).apply()

		val intent = Intent(this, NoteListActivity::class.java)
		startActivity(intent)
	}


}
