package com.example.livedataviewmodel.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.livedataviewmodel.presenter.UserDetailsApi
import com.example.simpleloginviewmodelivedata.view.IMainActivityView


class UserViewModel : ViewModel() {

    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()


    public var resultData = MutableLiveData<String>()
    var iMainActivityView: IMainActivityView? = null


    public fun getResult(): MutableLiveData<String> {
        if (resultData == null) {
            resultData = MutableLiveData()
        }
        return resultData
    }


    fun onClick(view: View) {
        if (email.value.isNullOrEmpty() || password.value.isNullOrEmpty()) {
            iMainActivityView?.onFailure()
            return
        }
        val userDetailsApi = UserDetailsApi()
        resultData = userDetailsApi.getUserList(email.value!!, password.value!!)
        iMainActivityView?.onSucess(resultData)

    }

}