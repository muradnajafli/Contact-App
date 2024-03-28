package com.example.contactapp.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.domain.model.Contact
import com.example.contactapp.databinding.ContactItemBinding

class ContactsAdapter : RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {
    private var contactList: List<Contact> = emptyList()

    class ContactsViewHolder(private val binding: ContactItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact) {
            binding.nameTextView.text = contact.name
            binding.numberTextView.text = contact.phoneNumber
            binding.root.setOnClickListener {
                it.context?.let { context ->
                    performCall(contact.phoneNumber, context)
                }
            }
        }

        private fun performCall(contactNumber: String, context: Context) {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$contactNumber")
            context.startActivity(intent)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val binding = ContactItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val item = contactList[position]
        holder.bind(item)
    }

    override fun getItemCount() = contactList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setContactsList(contacts: List<Contact>) {
        contactList = contacts
        notifyDataSetChanged()
    }
}