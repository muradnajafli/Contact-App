package com.muradnajafli.contactapp.di

import com.muradnajafli.contactapp.data.repository.ContactsRepositoryImpl
import com.muradnajafli.contactapp.domain.repository.ContactsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindContactsRepository(
        contactsRepositoryImpl: ContactsRepositoryImpl
    ): ContactsRepository

}