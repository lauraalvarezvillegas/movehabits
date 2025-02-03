package com.lauraalvarez.movehabits.data.di

import com.google.firebase.firestore.FirebaseFirestore
import com.lauraalvarez.movehabits.data.repository.WorkoutRepositoryImpl
import com.lauraalvarez.movehabits.domain.repository.WorkoutRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WorkoutRepositoryModule {

    @Provides
    @Singleton
    fun provideWorkoutRepository(
        firestore: FirebaseFirestore
    ): WorkoutRepository {
        return WorkoutRepositoryImpl(firestore)
    }
}
