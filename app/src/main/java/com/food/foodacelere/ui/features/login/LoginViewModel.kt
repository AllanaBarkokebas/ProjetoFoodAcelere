package com.food.foodacelere.ui.features.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.food.foodacelere.ui.app.*

class LoginViewModel : ViewModel() {

    val viewState = MutableLiveData<ViewState>()

    fun signIn(email: String, password: String) {
        val service = Service()
        viewState.value = LoadingData
        service.signInUser(email, password, { fireUser ->
            fireUser?.let {
                val user = User("", fireUser.email!!, "", "", "", "", true, it.uid!!)
                viewState.value = SuccessData(user)
            }
        }, {
            viewState.value = ErrorData(it)
        })
    }
}