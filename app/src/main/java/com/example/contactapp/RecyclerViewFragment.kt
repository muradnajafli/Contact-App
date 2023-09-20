package com.example.contactapp

import android.Manifest

import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactapp.data.Contact
import com.example.contactapp.databinding.FragmentRecyclerViewBinding

class RecyclerViewFragment : Fragment() {

    private val contactsList = mutableListOf<Contact>()
    private lateinit var adapter: ContactAdapter
    private lateinit var binding: FragmentRecyclerViewBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ContactAdapter(contactsList)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter


        if (checkPermission()) {
            getPhoneContacts()
        } else {
            requestPermission()
        }
    }

    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.READ_CONTACTS),
            1
        )
    }


    private fun getPhoneContacts() {
        val cursor = requireActivity().contentResolver.query(
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
    }
}
