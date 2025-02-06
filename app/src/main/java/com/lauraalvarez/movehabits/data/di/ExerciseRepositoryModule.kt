package com.lauraalvarez.movehabits.data.di

import com.google.firebase.firestore.FirebaseFirestore
import com.lauraalvarez.movehabits.data.repository.ExerciseRepositoryImpl
import com.lauraalvarez.movehabits.domain.repository.ExerciseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExerciseRepositoryModule {

    @Provides
    @Singleton
    fun provideExerciseRepository(firestore: FirebaseFirestore): ExerciseRepository {
        return ExerciseRepositoryImpl(firestore)
    }
}
