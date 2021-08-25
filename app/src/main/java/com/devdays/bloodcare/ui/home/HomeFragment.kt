package com.devdays.bloodcare.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devdays.bloodcare.databinding.HomeFragmentBinding
import com.devdays.bloodcare.util.getViewModelFactory

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var mHomeFragmentBinding: HomeFragmentBinding
    private val mHomeViewModel by viewModels<HomeViewModel> { getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mHomeFragmentBinding = HomeFragmentBinding.inflate(layoutInflater, container, false)
            .apply { homeViewModel = mHomeViewModel }
        return mHomeFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mHomeFragmentBinding.lifecycleOwner = viewLifecycleOwner
    }
}