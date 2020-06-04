package com.food.foodacelere.ui.features.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.food.foodacelere.ui.app.*

class CadastroViewModel : ViewModel() {

    val mUser = MutableLiveData<User>()
    val viewState = MutableLiveData<ViewState>()


    fun resisterNewUser(
        user: User
    ) {

        val service = Service()

        service.resisterUserEmailAndPassword(user.email, user.passowrd, { fireUser ->
            user.userId = fireUser!!.uid
            service.saveAdditionalUserInfoOnDataBase(user, {
                fireUser?.let {
                    mUser.value = user
                    viewState.value = SuccessData(user)
                }
            }, {
                viewState.value = ErrorData(it)
            })
        }, {
            viewState.value = ErrorData(it)
        })


    }


}