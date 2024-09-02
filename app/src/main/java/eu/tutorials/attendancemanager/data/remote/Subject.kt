package eu.tutorials.attendancemanager.data.remote

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subject-table")
data class Subject(
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0L,
    @ColumnInfo(name = "subject-subjectName")
    val subjectName: String = "",
    @ColumnInfo(name = "subject-present")
    var present: Int = 0,
    @ColumnInfo(name = "subject-totalClasses")
    var totalClasses: Int = 0
)
