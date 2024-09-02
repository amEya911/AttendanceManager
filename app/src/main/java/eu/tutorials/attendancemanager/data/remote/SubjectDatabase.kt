package eu.tutorials.attendancemanager.data.remote

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Subject::class],
    version = 1,
    exportSchema = false
)
abstract class SubjectDatabase : RoomDatabase() {
    abstract val subjectDao: SubjectDao
}