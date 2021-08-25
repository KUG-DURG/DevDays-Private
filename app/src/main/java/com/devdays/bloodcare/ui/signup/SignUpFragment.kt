package com.devdays.bloodcare.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devdays.bloodcare.databinding.SignUpFragmentBinding
import com.devdays.bloodcare.util.getViewModelFactory

class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    private lateinit var mSignUpFragmentBinding: SignUpFragmentBinding
    private val mSignUpViewModel by viewModels<SignUpViewModel> { getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mSignUpFragmentBinding = SignUpFragmentBinding.inflate(layoutInflater, container, false)
            .apply { signUpViewModel = mSignUpViewModel }
        return mSignUpFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mSignUpFragmentBinding.lifecycleOwner = viewLifecycleOwner
    }
}