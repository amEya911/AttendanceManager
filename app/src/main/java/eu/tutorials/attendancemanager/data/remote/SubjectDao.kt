package eu.tutorials.attendancemanager.data.remote

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import eu.tutorials.attendancemanager.data.remote.Subject
import kotlinx.coroutines.flow.Flow

@Dao
abstract class SubjectDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addASubject(subjectEntity: Subject)

    // Loads all wishes from the wish table
    @Query("Select * from `subject-table`")
    abstract fun getAllSubjects(): Flow<List<Subject>>

    @Update
    abstract suspend fun updateASubject(subjectEntity: Subject)

    @Delete
    abstract suspend fun deleteASubject(subjectEntity: Subject)

    @Query("Select * from `subject-table` where id=:id")
    abstract fun getASubjectById(id:Long): Flow<Subject>
}