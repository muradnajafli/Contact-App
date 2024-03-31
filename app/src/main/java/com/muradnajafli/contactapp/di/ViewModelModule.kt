package com.muradnajafli.contactapp.di

import com.muradnajafli.contactapp.domain.repository.ContactsRepository
import com.muradnajafli.contactapp.domain.usecase.GetContactsUseCase
import com.muradnajafli.contactapp.domain.usecase.GetContactsUseCaseImpl
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