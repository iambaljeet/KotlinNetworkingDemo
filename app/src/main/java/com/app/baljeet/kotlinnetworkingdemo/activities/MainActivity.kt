package com.app.baljeet.kotlinnetworkingdemo.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.app.baljeet.kotlinnetworkingdemo.R
import com.app.baljeet.kotlinnetworkingdemo.adapters.UsersRecyclerViewAdapter
import com.app.baljeet.kotlinnetworkingdemo.models.UserModel
import com.app.baljeet.kotlinnetworkingdemo.networking.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var disposable: Disposable? = null

    val apiService by lazy {
        ApiService.create()
    }

    var userModelList: ArrayList<UserModel> = ArrayList()
    var usersRecyclerViewAdapter: UsersRecyclerViewAdapter? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        getUsersList()
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        usersRecyclerViewAdapter = UsersRecyclerViewAdapter(userModelList)
        usersRecyclerView.layoutManager = layoutManager
        usersRecyclerView.adapter = usersRecyclerViewAdapter
    }

    fun getUsersList() {
        disposable =
                apiService.getUsers().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ result ->
                        userModelList.addAll(result)
                        usersRecyclerViewAdapter?.notifyDataSetChanged()
                    },
                        { error -> Log.e("MainActivity", error.message) })
    }
}