package com.devdays.bloodcare.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devdays.bloodcare.R
import com.devdays.bloodcare.databinding.SignUpFragmentBinding
import com.devdays.bloodcare.util.*
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import kotlin.collections.HashMap

class SignUpFragment : Fragment() {

    private lateinit var mSignUpFragmentBinding: SignUpFragmentBinding
    private val mSignUpViewModel by viewModels<SignUpViewModel> { getViewModelFactory() }

    private lateinit var mDatabaseReferenceSignUp: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mSignUpFragmentBinding = SignUpFragmentBinding.inflate(layoutInflater, container, false)
            .apply { signUpViewModel = mSignUpViewModel }
        return mSignUpFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mSignUpFragmentBinding.lifecycleOwner = viewLifecycleOwner

        mDatabaseReferenceSignUp =
            FirebaseDatabase.getInstance().reference.child("auth")

        setUpSignUpSnackbar()
        setUpSignUpNavigation()
    }

    private fun setUpSignUpSnackbar() {
        view?.setUpSnackbar(
            this@SignUpFragment.viewLifecycleOwner,
            mSignUpViewModel.mSignUpSnackbarMessage,
            Snackbar.LENGTH_SHORT
        )
    }

    private fun setUpSignUpNavigation() {
        mSignUpViewModel.mSignUpEvent.observe(viewLifecycleOwner, EventObserver {
            context?.let {
                if (NetworkUtils.isNetworkConnected(it)) {
                    val mHashMapAddSignUp: HashMap<String, String> = HashMap()
                    mHashMapAddSignUp["fullName"] =
                        mSignUpViewModel.mSignUpFullName.get().toString()
                    mHashMapAddSignUp["emailId"] =
                        mSignUpViewModel.mSignUpEmailId.get().toString()
                    mHashMapAddSignUp["mobileNumber"] =
                        mSignUpViewModel.mSignUpMobileNumber.get().toString()
                    mHashMapAddSignUp["password"] =
                        mSignUpViewModel.mSignUpPassword.get().toString()
                    mHashMapAddSignUp["password"] =
                        mSignUpViewModel.mSignUpLocation.get().toString()
                    mHashMapAddSignUp["bloodGroup"] =
                        mSignUpViewModel.mSignUpBloodGroup.get().toString()

                    mDatabaseReferenceSignUp.push()
                        .setValue(mHashMapAddSignUp)
                        .addOnCompleteListener {
                            findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)

                            Toast.makeText(
                                context,
                                getString(R.string.text_sign_up_successfull),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                } else {
                    view?.toast(it, getString(R.string.text_error_internet), Toast.LENGTH_SHORT)
                }
            }
        })
        mSignUpViewModel.mSignUpSignInEvent.observe(viewLifecycleOwner, EventObserver {
            findNavController().popBackStack()
        })
    }
}