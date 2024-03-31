package com.example.contactapp.domain.usecase

import com.example.contactapp.domain.model.Contact
import com.example.contactapp.domain.repository.ContactsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetContactsUseCaseImpl @Inject constructor(
    private val contactsRepository: ContactsRepository
) : GetContactsUseCase {
    override suspend fun invoke(): Flow<List<Contact>> {
        return contactsRepository.getContacts()
    }
}

interface GetContactsUseCase {
    suspend operator fun invoke(): Flow<List<Contact>>
}