package com.devdays.bloodcare.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devdays.bloodcare.databinding.ProfileFragmentBinding
import com.devdays.bloodcare.util.getViewModelFactory

class ProfileFragment : Fragment() {

    private lateinit var mProfileFragmentBinding: ProfileFragmentBinding
    private val mProfileViewModel by viewModels<ProfileViewModel> { getViewModelFactory() }

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
    }
}