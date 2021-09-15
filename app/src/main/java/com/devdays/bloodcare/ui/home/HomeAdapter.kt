package com.devdays.bloodcare.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devdays.bloodcare.databinding.ItemHomeBinding

class HomeAdapter(
    private var mHomeViewModel: HomeViewModel,
    var mHomeModelList: List<HomeModel>
) : ListAdapter<HomeModel, HomeAdapter.HomeViewHolder>(HomeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder.from(parent) as HomeViewHolder
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val mHomeListItem = mHomeModelList[position]
        holder.bind(mHomeViewModel, mHomeListItem)
    }

    override fun getItemCount(): Int = mHomeModelList.size

    class HomeViewHolder private constructor(private var mItemHomeBinding: ItemHomeBinding) :
        RecyclerView.ViewHolder(mItemHomeBinding.root) {
        fun bind(
            mHomeViewModel: HomeViewModel,
            mHomeModel: HomeModel
        ) {
            mItemHomeBinding.homeViewModel = mHomeViewModel
            mItemHomeBinding.homeModel = mHomeModel
            mItemHomeBinding.executePendingBindings()
        }

        companion object {
            fun from(mViewGroup: ViewGroup): RecyclerView.ViewHolder {
                return HomeViewHolder(
                    ItemHomeBinding.inflate(
                        LayoutInflater.from(mViewGroup.context),
                        mViewGroup,
                        false
                    )
                )
            }
        }
    }
}

class HomeDiffCallback : DiffUtil.ItemCallback<HomeModel>() {
    override fun areItemsTheSame(
        mHomeModelOldItem: HomeModel,
        mHomeModelNewItem: HomeModel
    ): Boolean {
        return mHomeModelOldItem.mId == mHomeModelNewItem.mId
    }

    override fun areContentsTheSame(
        mHomeModelOldItem: HomeModel,
        mHomeModelNewItem: HomeModel
    ): Boolean {
        return mHomeModelOldItem == mHomeModelNewItem
    }
}