package com.example.contactapp.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactapp.databinding.FragmentContactsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ContactsFragment : Fragment() {

    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ContactsViewModel by viewModels()
    private lateinit var contactsAdapter: ContactsAdapter

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            viewModel.loadContacts()
        } else {
            Toast.makeText(requireContext(), "Permission denied.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        performPermissionAction()
        observeViewModel()
    }

    private fun performPermissionAction() {
        if (checkPermission()) {
            viewModel.loadContacts()
        } else {
            requestPermission()
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            contactsAdapter = ContactsAdapter()
            adapter = contactsAdapter
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.contactList.collect { contactList ->
                contactsAdapter.setContactsList(contactList)
            }
        }
    }

    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}