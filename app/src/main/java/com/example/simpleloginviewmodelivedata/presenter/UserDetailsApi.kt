package com.example.livedataviewmodel.presenter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.livedataviewmodel.model.UserData
import dev.ashish.recyclerviewwithviewmodellivedata.retrofit.ApiClient
import dev.ashish.recyclerviewwithviewmodellivedata.retrofit.ApiInterface
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailsApi {

    private var logindata = MutableLiveData<String>()
//    private var arrayListUserViewModel = ArrayList<UserViewModel>()

    public fun getUserList(email: String, password: String): MutableLiveData<String> {

        val service = ApiClient.createService(ApiInterface::class.java)
        val call = service.getUserDetails(email, password)

        call.enqueue(object : Callback<ResponseBody> {

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                try {
                    Log.d("UserDetailsApi", "onResponse")
                    if (response!!.isSuccessful) {

                        val jsonObject = JSONObject(response.body().string())
                        logindata.value = jsonObject.getString("token")

                    } else {
                        logindata.value = "Something went wrong"
                        println("Something went wrong")
                    }

                } catch (e: Exception) {
                    println(e.message)
                }
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                println(t!!.message)
            }

        })

        return logindata
    }
}