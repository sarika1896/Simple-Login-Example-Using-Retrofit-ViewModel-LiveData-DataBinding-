package dev.ashish.recyclerviewwithviewmodellivedata.retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @FormUrlEncoded
    @POST("api/login")
    fun getUserDetails(
        @Field("email") email: String,
        @Field("password") password: String): Call<ResponseBody>
}
