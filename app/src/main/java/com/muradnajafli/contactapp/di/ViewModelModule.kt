package com.example.contactapp.di

import com.example.contactapp.domain.repository.ContactsRepository
import com.example.contactapp.domain.usecase.GetContactsUseCase
import com.example.contactapp.domain.usecase.GetContactsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @ViewModelScoped
    @Provides
    fun provideGetContactsUseCase(
        contactsRepository: ContactsRepository
    ): GetContactsUseCase {
        return GetContactsUseCaseImpl(contactsRepository)
    }

}