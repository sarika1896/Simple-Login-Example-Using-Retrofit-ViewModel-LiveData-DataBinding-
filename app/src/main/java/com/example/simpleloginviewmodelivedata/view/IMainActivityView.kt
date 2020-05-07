package com.example.simpleloginviewmodelivedata.view

import androidx.lifecycle.MutableLiveData

interface IMainActivityView {

    fun onSucess(resultData: MutableLiveData<String>)
    fun onFailure()
}