package at.fh.swengb.loecker.homeexercise2

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Note (@PrimaryKey (autoGenerate = true) val id: Int = 0, val title: String, val content: String) {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as Note

		if (title != other.title) return false
		if (content != other.content) return false

		return true
	}

	override fun hashCode(): Int {
		var result = title.hashCode()
		result = 31 * result + content.hashCode()
		return result
	}

}
