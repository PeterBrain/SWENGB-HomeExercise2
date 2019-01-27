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
class Note (val title: String, val content: String, @ColumnInfo(name = "userId", index = true) val userId: Long) {
	@PrimaryKey (autoGenerate = true)
	var id: Long = 0
}
