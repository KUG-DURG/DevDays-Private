package com.devdays.bloodcare.ui.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devdays.bloodcare.R
import com.devdays.bloodcare.databinding.SignInFragmentBinding
import com.devdays.bloodcare.util.*
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.*

class SignInFragment : Fragment() {

    private lateinit var mSignInFragmentBinding: SignInFragmentBinding
    private val mSignInViewModel by viewModels<SignInViewModel> { getViewModelFactory() }

    private lateinit var mSignInDatabaseReference: DatabaseReference
    private lateinit var mSignInSharedPreferenceUtils: SharedPreferenceUtils

    private var mEmailId = ""
    private var mPassword = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mSignInFragmentBinding =
            SignInFragmentBinding.inflate(layoutInflater, container, false).apply {
                signInViewModel = mSignInViewModel
            }
        return mSignInFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mSignInFragmentBinding.lifecycleOwner = viewLifecycleOwner

        mSignInDatabaseReference = FirebaseDatabase.getInstance().reference.child("auth")

        setUpSignInSnackbar()
        setUpSignInNavigation()
    }

    private fun setUpSignInSnackbar() {
        view?.setUpSnackbar(
            this@SignInFragment.viewLifecycleOwner,
            mSignInViewModel.mSignInSnackbarMessage,
            Snackbar.LENGTH_SHORT
        )
    }

    private fun setUpSignInNavigation() {
        mSignInViewModel.mSignInEvent.observe(viewLifecycleOwner, EventObserver {
            context?.let {
                if (NetworkUtils.isNetworkConnected(it)) {
                    mSignInDatabaseReference.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            mEmailId = snapshot.child("emailId").value.toString()
                            mPassword = snapshot.child("password").value.toString()

                            mSignInSharedPreferenceUtils = SharedPreferenceUtils(it)

                            if (mEmailId == mSignInViewModel.mSignInEmailId.get()
                                    .toString() && mPassword == mSignInViewModel.mSignInPassword.get()
                                    .toString()
                            ) {
                                mSignInSharedPreferenceUtils.mLoginToken = true
                                findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
                            } else {
                                mSignInSharedPreferenceUtils.mLoginToken = false
                                view?.toast(
                                    it,
                                    getString(R.string.text_error_invalid_credentials),
                                    Toast.LENGTH_SHORT
                                )
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
        })
        mSignInViewModel.mSignInSignUpEvent.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        })
    }

    companion object {
        private const val TAG = "SignInFragment"
    }
}