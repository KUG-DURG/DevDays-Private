package com.devdays.bloodcare.ui.find

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devdays.bloodcare.databinding.FindFragmentBinding
import com.devdays.bloodcare.util.getViewModelFactory

class FindFragment : Fragment() {

    companion object {
        fun newInstance() = FindFragment()
    }

    private lateinit var mFindFragmentBinding: FindFragmentBinding
    private val mFindViewModel by viewModels<FindViewModel> { getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mFindFragmentBinding = FindFragmentBinding.inflate(layoutInflater, container, false)
            .apply { findViewModel = mFindViewModel }
        return mFindFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mFindFragmentBinding.lifecycleOwner = viewLifecycleOwner
    }
}