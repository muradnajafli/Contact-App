package com.example.contactapp.domain.repository

import com.example.contactapp.domain.model.Contact
import kotlinx.coroutines.flow.Flow

interface ContactsRepository {
    fun getContacts(): Flow<List<Contact>>
}