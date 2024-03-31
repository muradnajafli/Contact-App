package com.muradnajafli.contactapp.domain.repository

import com.muradnajafli.contactapp.domain.model.Contact
import kotlinx.coroutines.flow.Flow

interface ContactsRepository {
    fun getContacts(): Flow<List<Contact>>
}