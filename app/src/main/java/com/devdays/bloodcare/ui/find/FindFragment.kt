package com.devdays.bloodcare.ui.find

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devdays.bloodcare.databinding.FindFragmentBinding
import com.devdays.bloodcare.util.EventObserver
import com.devdays.bloodcare.util.getViewModelFactory
import com.devdays.bloodcare.util.setUpSnackbar
import com.google.android.material.snackbar.Snackbar

class FindFragment : Fragment() {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFindFragmentBinding.lifecycleOwner = viewLifecycleOwner

        setUpFindSnackbar()
        setUpFindNavigation()
    }

    private fun setUpFindSnackbar() {
        view?.setUpSnackbar(
            this@FindFragment.viewLifecycleOwner,
            mFindViewModel.mFindSnackbarMessage,
            Snackbar.LENGTH_SHORT
        )
    }

    private fun setUpFindNavigation() {
        mFindViewModel.mFindSubmitRequestEvent.observe(viewLifecycleOwner, EventObserver {})
    }
}