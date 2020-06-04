package com.food.foodacelere.ui.app

data class User(
    var fullName: String,
    var email: String,
    var phoneNumber: String,
    var birthDay: String,
    var document: String,
    var passowrd: String,
    var terms: Boolean,
    var userId: String
)


sealed class ViewState()
class SuccessData(val user: User) : ViewState()
class ErrorData(val errorMessage: String?) : ViewState()
object LoadingData : ViewState()