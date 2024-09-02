package eu.tutorials.attendancemanager.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tutorials.attendancemanager.data.remote.Subject
import eu.tutorials.attendancemanager.data.repository.SubjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val subjectRepository: SubjectRepository
): ViewModel() {

    var subjectNameState by mutableStateOf("")
    var presentState by mutableStateOf(0)
    var totalClassesState by mutableStateOf(0)
    var totalAttendance by mutableStateOf(0.0)

    fun onSubjectNameChanged(newString: String) {
        subjectNameState = newString
    }

    fun onPresentChanged(newInt: Int) {
        presentState = newInt
    }

    fun onTotalClassesChanged(newInt: Int) {
        totalClassesState = newInt
    }

    lateinit var getAllSubjects: Flow<List<Subject>>

    init {
        viewModelScope.launch {
            getAllSubjects = subjectRepository.getSubjects()
        }
    }

    fun addSubject(subject: Subject) {
        viewModelScope.launch(Dispatchers.IO) {
            subjectRepository.addASubject(subject)
        }
    }

    private fun updateSubject(subject: Subject) {
        viewModelScope.launch(Dispatchers.IO) {
            subjectRepository.updateASubject(subject)
        }
    }

    fun deleteSubject(subject: Subject) {
        viewModelScope.launch(Dispatchers.IO) {
            subjectRepository.deleteASubject(subject)
        }
    }

    fun onPresent(subject: Subject) {
        viewModelScope.launch(Dispatchers.IO) {
            val updatedSubject = subject.copy(
                present = subject.present + 1,
                totalClasses = subject.totalClasses + 1
            )
            updateSubject(updatedSubject)
        }
    }

    fun onAbsent(subject: Subject) {
        viewModelScope.launch(Dispatchers.IO) {
            val updatedSubject = subject.copy(
                totalClasses = subject.totalClasses + 1
            )
            updateSubject(updatedSubject)
        }
    }
}