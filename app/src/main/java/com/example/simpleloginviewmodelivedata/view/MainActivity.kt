package com.example.simpleloginviewmodelivedata.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.livedataviewmodel.viewmodel.UserViewModel
import com.example.simpleloginviewmodelivedata.R
import com.example.simpleloginviewmodelivedata.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), IMainActivityView {

    private val userViewModel by lazy {
        ViewModelProviders.of(this).get(UserViewModel::class.java)
    }
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        activityMainBinding.setLifecycleOwner(this)
        activityMainBinding.loginmodel = userViewModel

        userViewModel.iMainActivityView = this


    }

    override fun onSucess(resultData: MutableLiveData<String>) {
        resultData.observe(this, Observer {
            activityMainBinding.textviewData.setText(it)
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()

        })

    }

    override fun onFailure() {
        Toast.makeText(this, "something went wrong", Toast.LENGTH_LONG).show()
    }
}
