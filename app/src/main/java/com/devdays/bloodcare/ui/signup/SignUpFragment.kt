package com.devdays.bloodcare.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devdays.bloodcare.databinding.SignUpFragmentBinding
import com.devdays.bloodcare.util.EventObserver
import com.devdays.bloodcare.util.getViewModelFactory
import com.devdays.bloodcare.util.setUpSnackbar
import com.google.android.material.snackbar.Snackbar

class SignUpFragment : Fragment() {

    private lateinit var mSignUpFragmentBinding: SignUpFragmentBinding
    private val mSignUpViewModel by viewModels<SignUpViewModel> { getViewModelFactory() }

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
        mSignUpViewModel.mSignUpEvent.observe(viewLifecycleOwner, EventObserver {})
        mSignUpViewModel.mSignUpSignInEvent.observe(viewLifecycleOwner, EventObserver {})
    }
}