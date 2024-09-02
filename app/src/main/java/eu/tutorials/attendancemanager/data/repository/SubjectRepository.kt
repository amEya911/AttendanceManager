package eu.tutorials.attendancemanager.data.repository

import eu.tutorials.attendancemanager.data.remote.Subject
import eu.tutorials.attendancemanager.data.remote.SubjectDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubjectRepository @Inject constructor(private val subjectDao: SubjectDao) {

    suspend fun addASubject(subject: Subject) {
        subjectDao.addASubject(subject)
    }

    fun getSubjects():Flow<List<Subject>> = subjectDao.getAllSubjects()

    fun getASubjectById(id:Long) :Flow<Subject> {
        return subjectDao.getASubjectById(id)
    }

    suspend fun updateASubject(subject: Subject) {
        subjectDao.updateASubject(subject)
    }

    suspend fun deleteASubject(subject: Subject) {
        subjectDao.deleteASubject(subject)
    }
}