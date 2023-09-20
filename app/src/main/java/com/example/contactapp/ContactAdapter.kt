package com.example.contactapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.data.Contact
import com.example.contactapp.databinding.ItemContactBinding

class ContactAdapter(
    private val contactList: List<Contact>
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {
        val name = binding.nameTextView
        val number = binding.numberTextView

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedContact = contactList[position]
                    val contactNumber = clickedContact.phoneNumber
                    performCall(itemView.context, contactNumber)
                }
            }
        }

    }
    private fun performCall(context: Context, contactNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$contactNumber")
        context.startActivity(intent)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(ItemContactBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false))
    }


    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val item = contactList[position]
        holder.name.text = item.name
        holder.number.text = item.phoneNumber
    }

    override fun getItemCount() = contactList.size
}
