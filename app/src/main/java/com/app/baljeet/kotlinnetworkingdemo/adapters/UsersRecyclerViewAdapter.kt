package com.app.baljeet.kotlinnetworkingdemo.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.baljeet.kotlinnetworkingdemo.R
import com.app.baljeet.kotlinnetworkingdemo.databinding.UsersListItemBinding
import com.app.baljeet.kotlinnetworkingdemo.models.UserModel

class UsersRecyclerViewAdapter(var userModelList: ArrayList<UserModel>) :
    RecyclerView.Adapter<UsersRecyclerViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): MyViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(viewGroup.context)
        val binding: UsersListItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.users_list_item, viewGroup, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userModelList.size
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        viewHolder.bind(userModelList.get(position))
    }

    class MyViewHolder(var dataBinding: UsersListItemBinding) : RecyclerView.ViewHolder(dataBinding.root) {
        private var mBinding: UsersListItemBinding

        init {
            mBinding = dataBinding
        }

        fun bind(userModel: UserModel) {
            mBinding.users = userModel
        }
    }
}