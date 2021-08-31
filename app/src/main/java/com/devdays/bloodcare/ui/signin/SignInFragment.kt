package com.devdays.bloodcare.ui.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devdays.bloodcare.databinding.SignInFragmentBinding
import com.devdays.bloodcare.util.EventObserver
import com.devdays.bloodcare.util.getViewModelFactory
import com.devdays.bloodcare.util.setUpSignInSnackbar
import com.google.android.material.snackbar.Snackbar

class SignInFragment : Fragment() {

    private lateinit var mSignInFragmentBinding: SignInFragmentBinding
    private val mSignInViewModel by viewModels<SignInViewModel> { getViewModelFactory() }

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

        setUpSignInSnackbar()
        setUpSignInNavigation()
    }

    private fun setUpSignInSnackbar() {
        view?.setUpSignInSnackbar(
            this@SignInFragment.viewLifecycleOwner,
            mSignInViewModel.mSignInSnackbarMessage,
            Snackbar.LENGTH_SHORT
        )
    }

    private fun setUpSignInNavigation() {
        mSignInViewModel.mSignInEvent.observe(viewLifecycleOwner, EventObserver {})
        mSignInViewModel.mSignInForgotPasswordEvent.observe(viewLifecycleOwner, EventObserver {})
        mSignInViewModel.mSignInSignUpEvent.observe(viewLifecycleOwner, EventObserver {})
    }
}