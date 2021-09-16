package com.devdays.bloodcare.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devdays.bloodcare.databinding.HomeFragmentBinding
import com.devdays.bloodcare.util.getViewModelFactory
import com.devdays.bloodcare.util.toast

class HomeFragment : Fragment() {

    private lateinit var mHomeFragmentBinding: HomeFragmentBinding
    private val mHomeViewModel by viewModels<HomeViewModel> { getViewModelFactory() }

    private lateinit var mHomeAdapter: HomeAdapter
    private var mHomeModel: ArrayList<HomeModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mHomeFragmentBinding = HomeFragmentBinding.inflate(layoutInflater, container, false)
            .apply { homeViewModel = mHomeViewModel }
        return mHomeFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mHomeFragmentBinding.lifecycleOwner = viewLifecycleOwner
        setUpHomeAdapter()
    }

    private fun setUpHomeAdapter() {
        val mViewModel = mHomeFragmentBinding.homeViewModel

        if (mViewModel != null) {
            mHomeAdapter =
                HomeAdapter(mViewModel, mHomeModel)
            mHomeFragmentBinding.recyclerViewHome.adapter =
                mHomeAdapter
        } else {
            context?.let {
                view?.toast(
                    it,
                    "ViewModel not initialized when attempting to set up adapter.",
                    Toast.LENGTH_SHORT
                )
            }
        }
    }
}