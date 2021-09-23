package com.devdays.bloodcare.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devdays.bloodcare.R
import com.devdays.bloodcare.databinding.ProfileFragmentBinding
import com.devdays.bloodcare.util.NetworkUtils
import com.devdays.bloodcare.util.getViewModelFactory
import com.devdays.bloodcare.util.toast
import com.google.firebase.database.*

class ProfileFragment : Fragment() {

    private lateinit var mProfileFragmentBinding: ProfileFragmentBinding
    private val mProfileViewModel by viewModels<ProfileViewModel> { getViewModelFactory() }

    private lateinit var mProfileDatabaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mProfileFragmentBinding = ProfileFragmentBinding.inflate(layoutInflater, container, false)
            .apply { profileViewModel = mProfileViewModel }
        return mProfileFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mProfileFragmentBinding.lifecycleOwner = viewLifecycleOwner

        mProfileDatabaseReference = FirebaseDatabase.getInstance().reference.child("auth")

        onLoadProfileDataFromFirebase()
    }

    private fun onLoadProfileDataFromFirebase() {
        context?.let {
            if (NetworkUtils.isNetworkConnected(it)) {
                mProfileDatabaseReference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {

                        for (mProfileData in snapshot.children) {
                            mProfileViewModel.mProfileFullName.set(mProfileData.child("fullName").value.toString())
                            mProfileViewModel.mProfileEmailId.set(mProfileData.child("emailId").value.toString())
                            mProfileViewModel.mProfileMobileNumber.set(mProfileData.child("mobileNumber").value.toString())
                            mProfileViewModel.mProfilePassword.set(mProfileData.child("password").value.toString())
                            mProfileViewModel.mProfileLocation.set(mProfileData.child("location").value.toString())
                            mProfileViewModel.mProfileBloodGroup.set(mProfileData.child("bloodGroup").value.toString())
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {}
                })
            } else {
                view?.toast(
                    it,
                    getString(R.string.text_error_internet),
                    Toast.LENGTH_SHORT
                )
            }
        }
    }
}