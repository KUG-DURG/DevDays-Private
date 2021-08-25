package com.devdays.bloodcare.ui.forgotpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devdays.bloodcare.databinding.ForgotPasswordFragmentBinding
import com.devdays.bloodcare.util.getViewModelFactory

class ForgotPasswordFragment : Fragment() {

    companion object {
        fun newInstance() = ForgotPasswordFragment()
    }

    private lateinit var mForgotPasswordFragmentBinding: ForgotPasswordFragmentBinding
    private val mForgotPasswordViewModel by viewModels<ForgotPasswordViewModel> { getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mForgotPasswordFragmentBinding =
            ForgotPasswordFragmentBinding.inflate(layoutInflater, container, false)
                .apply { forgotPasswordViewModel = mForgotPasswordViewModel }
        return mForgotPasswordFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mForgotPasswordFragmentBinding.lifecycleOwner = viewLifecycleOwner
    }
}