package com.example.contactapp.data.repository

import android.content.ContentResolver
import android.provider.ContactsContract
import com.example.contactapp.domain.model.Contact
import com.example.contactapp.domain.repository.ContactsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ContactsRepositoryImpl @Inject constructor(
    private val contentResolver: ContentResolver
) : ContactsRepository {

    override fun getContacts(): Flow<List<Contact>> = flow {
        val contactsList = mutableListOf<Contact>()
        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )

        cursor?.use {
            while (it.moveToNext()) {
                val nameColumnIndex =
                    it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                val numberColumnIndex =
                    it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                val contactName = it.getString(nameColumnIndex)
                val contactNumber = it.getString(numberColumnIndex)

                val contact = Contact(contactName, contactNumber)
                contactsList.add(contact)
            }
        }
        emit(contactsList)
    }

}