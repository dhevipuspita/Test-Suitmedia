package com.dhevi.suitmediaapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dhevi.suitmediaapp.api.ApiConfig
import com.dhevi.suitmediaapp.data.DataItem
import com.dhevi.suitmediaapp.data.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThirdScreenViewModel {
    private val listUser = MutableLiveData<List<DataItem>>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun setUsers() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUsers(1,12)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                _isLoading.value = false
                if (response.isSuccessful){
                    listUser.postValue(response.body()?.data as List<DataItem>?)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isLoading.value = true
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun getUsers(): LiveData<List<DataItem>> {
        return listUser
    }

    companion object {
        private const val TAG = "ThirdScreenViewModel"
    }
}