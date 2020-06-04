package com.food.foodacelere.ui.features.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.food.foodacelere.ui.app.Service

class SelectSignatureViewModel : ViewModel(){

    val isUserLoged = MutableLiveData<StateLog>()

    fun isLogedIn() {
        val service = Service()
        if (service.isUserLogedOnFirebase()) {
            isUserLoged.value = StateLog(StateLog.Companion.STATE.LOGDED)
        } else {
            isUserLoged.value = StateLog(StateLog.Companion.STATE.NOTLOGED)
        }

    }

}