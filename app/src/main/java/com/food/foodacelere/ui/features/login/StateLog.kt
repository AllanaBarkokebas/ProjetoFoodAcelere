package com.food.foodacelere.ui.features.login

data class StateLog(val status: STATE){
    companion object {
        enum class STATE {
            LOGDED, NOTLOGED
        }
    }
}