package com.devdays.bloodcare.ui.find

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devdays.bloodcare.R
import com.devdays.bloodcare.databinding.FindFragmentBinding
import com.devdays.bloodcare.util.*
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FindFragment : Fragment() {

    private lateinit var mFindFragmentBinding: FindFragmentBinding
    private val mFindViewModel by viewModels<FindViewModel> { getViewModelFactory() }

    private lateinit var mDatabaseReferenceFind: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mFindFragmentBinding = FindFragmentBinding.inflate(layoutInflater, container, false)
            .apply { findViewModel = mFindViewModel }
        return mFindFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFindFragmentBinding.lifecycleOwner = viewLifecycleOwner

        mDatabaseReferenceFind =
            FirebaseDatabase.getInstance().reference.child("requests")

        setUpFindSnackbar()
        setUpFindNavigation()
    }

    private fun setUpFindSnackbar() {
        view?.setUpSnackbar(
            this@FindFragment.viewLifecycleOwner,
            mFindViewModel.mFindSnackbarMessage,
            Snackbar.LENGTH_SHORT
        )
    }

    private fun setUpFindNavigation() {
        mFindViewModel.mFindSubmitRequestEvent.observe(viewLifecycleOwner, EventObserver {
            context?.let {
                if (NetworkUtils.isNetworkConnected(it)) {
                    val mHashMapAddFind: HashMap<String, String> = HashMap()
                    mHashMapAddFind["fullName"] =
                        mFindViewModel.mFindFullName.get().toString()
                    mHashMapAddFind["mobileNumber"] =
                        mFindViewModel.mFindMobileNumber.get().toString()
                    mHashMapAddFind["relation"] =
                        mFindViewModel.mFindRelation.get().toString()
                    mHashMapAddFind["bloodGroup"] =
                        mFindViewModel.mFindBloodGroup.get().toString()
                    mHashMapAddFind["location"] =
                        mFindViewModel.mFindLocation.get().toString()

                    mDatabaseReferenceFind.push()
                        .setValue(mHashMapAddFind)
                        .addOnCompleteListener {
                            Toast.makeText(
                                context,
                                getString(R.string.text_message_request_success),
                                Toast.LENGTH_SHORT
                            ).show()

                            mFindFragmentBinding.textInputEditTextFindFullName.text?.clear()
                            mFindFragmentBinding.textInputEditTextFindMobileNumber.text?.clear()
                            mFindFragmentBinding.textInputEditTextFindRelation.text?.clear()
                            mFindFragmentBinding.textInputEditTextFindBloodGroup.text?.clear()
                            mFindFragmentBinding.textInputEditTextFindLocation.text?.clear()
                        }
                } else {
                    view?.toast(it, getString(R.string.text_error_internet), Toast.LENGTH_SHORT)
                }
            }
        })
    }
}