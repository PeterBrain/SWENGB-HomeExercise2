package at.fh.swengb.loecker.homeexercise2

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(
	tableName = "notes",
	foreignKeys = [ForeignKey(
		entity = User::class,
		parentColumns = ["id"],
		childColumns = ["userId"],
		onDelete = ForeignKey.CASCADE
	)]
)
class Note (var title: String, var content: String, @ColumnInfo(name = "userId", index = true) val userId: Long) {
	@PrimaryKey (autoGenerate = true)
	var id: Long = 0

	companion object {
		val EXTRA_NOTE_ID = "NOTE_ID_EXTRA"
	}
}
