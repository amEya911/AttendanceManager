package eu.tutorials.attendancemanager.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import eu.tutorials.attendancemanager.data.remote.SubjectDao
import eu.tutorials.attendancemanager.data.remote.SubjectDatabase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun providesSubjectDatabase(@ApplicationContext context: Context): SubjectDatabase {
        return Room.databaseBuilder(
            context,
            SubjectDatabase::class.java,
            "Subject.db"
        ).build()
    }


    @Provides
    @Singleton
    fun provideNoteDao(subjectDatabase: SubjectDatabase): SubjectDao {
        return subjectDatabase.subjectDao
    }
}