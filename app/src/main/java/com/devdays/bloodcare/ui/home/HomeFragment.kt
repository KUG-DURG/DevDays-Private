package com.devdays.bloodcare.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devdays.bloodcare.R
import com.devdays.bloodcare.databinding.HomeFragmentBinding
import com.devdays.bloodcare.util.AlertDialogUtils
import com.devdays.bloodcare.util.NetworkUtils
import com.devdays.bloodcare.util.getViewModelFactory
import com.devdays.bloodcare.util.toast
import com.google.firebase.database.*

class HomeFragment : Fragment() {

    private lateinit var mHomeFragmentBinding: HomeFragmentBinding
    private val mHomeViewModel by viewModels<HomeViewModel> { getViewModelFactory() }

    private lateinit var mHomeAdapter: HomeAdapter
    private var mHomeModel: ArrayList<HomeModel> = ArrayList()

    private lateinit var mDatabaseReferenceHome: DatabaseReference

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

        mDatabaseReferenceHome = FirebaseDatabase.getInstance().reference.child("requests")
        setUpHomeAdapter()
        onBackPress()
    }

    private fun setUpHomeAdapter() {
        context?.let {
            if (NetworkUtils.isNetworkConnected(it)) {
                mDatabaseReferenceHome.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {

                        if (snapshot.value != null) {
                            mHomeFragmentBinding.recyclerViewHome.visibility = View.VISIBLE
                            mHomeFragmentBinding.materialtextViewHomeNoDataFound.visibility =
                                View.GONE

                            mHomeModel.clear()
                            for (mHomeData in snapshot.children) {
                                mHomeModel.add(
                                    HomeModel(
                                        mHomeData.child("id").value.toString(),
                                        mHomeData.child("fullName").value.toString(),
                                        mHomeData.child("mobileNumber").value.toString(),
                                        mHomeData.child("relation").value.toString(),
                                        mHomeData.child("bloodGroup").value.toString(),
                                        mHomeData.child("location").value.toString(),
                                    )
                                )
                            }

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
                        } else {
                            mHomeFragmentBinding.recyclerViewHome.visibility = View.GONE
                            mHomeFragmentBinding.materialtextViewHomeNoDataFound.visibility =
                                View.VISIBLE
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        mHomeFragmentBinding.recyclerViewHome.visibility = View.GONE
                        mHomeFragmentBinding.materialtextViewHomeNoDataFound.visibility =
                            View.VISIBLE
                    }
                })
            } else {
                view?.toast(
                    it,
                    getString(R.string.text_error_internet),
                    Toast.LENGTH_SHORT
                )
            }
        }
    }

    private fun onBackPress() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    context?.let {
                        AlertDialogUtils.getInstance().showAlert(
                            it,
                            R.drawable.ic_warning,
                            getString(R.string.text_alert_title_close_app),
                            getString(R.string.text_alert_message_confirm),
                            getString(android.R.string.ok),
                            { dialog, _ ->
                                activity?.finish()
                                dialog.dismiss()
                            },
                            getString(android.R.string.cancel),
                            { dialog, _ ->
                                dialog.dismiss()
                            }
                        )
                    }
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }
}