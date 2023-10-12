package com.example.contactapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.data.Contact
import com.example.contactapp.databinding.ItemContactBinding

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    private var contactList: List<Contact> = emptyList()
    private var context: Context? = null

    inner class ContactViewHolder(val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun setContactsList(contacts: List<Contact>) {
        contactList = contacts
        notifyDataSetChanged()
    }

    fun setContext(context: Context) {
        this.context = context
    }

    private fun performCall(contactNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$contactNumber")
        context?.startActivity(intent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val item = contactList[position]
        onBind(holder, item)
    }

    override fun getItemCount() = contactList.size

    private fun onBind(holder: ContactViewHolder, contact: Contact) {
        val contactHolder = holder.binding
        contactHolder.nameTextView.text = contact.name
        contactHolder.numberTextView.text = contact.phoneNumber

        contactHolder.root.setOnClickListener {
            context?.let {
                performCall(contact.phoneNumber)
            }
        }
    }
}