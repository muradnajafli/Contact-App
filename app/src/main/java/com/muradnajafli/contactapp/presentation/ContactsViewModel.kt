package com.muradnajafli.contactapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muradnajafli.contactapp.domain.model.Contact
import com.muradnajafli.contactapp.domain.usecase.GetContactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val getContactsUseCase: GetContactsUseCase
) : ViewModel() {

    private val _contactList = MutableStateFlow<List<Contact>>(emptyList())
    val contactList = _contactList.asStateFlow()

    init {
        loadContacts()
    }

    fun loadContacts() {
        viewModelScope.launch {
            getContactsUseCase().collect { contacts ->
                _contactList.value = contacts
            }
        }
    }

}