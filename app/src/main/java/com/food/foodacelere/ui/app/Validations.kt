package com.food.foodacelere.ui.app

class Validations {
    companion object {
        fun String.validateEmail(): Boolean =
            this.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()

        fun String.validateName(): Boolean = this.isNotEmpty() || this.isNotBlank()
        fun String.validatePassword(): Boolean = this.isNotEmpty() && (this.length >= 8)
        fun validateSharedPreferencesData(
            userId: String?,
            userName: String?,
            userEmail: String?,
            userProfile: String?
        ): Boolean {
            return !(userId!!.isEmpty() || userName!!.isEmpty() || userEmail!!.isEmpty() || userProfile!!.isEmpty())
        }

        fun String.validateBirthDate(): Boolean {
            try {
                val cleanDate = this.replace("/", "")
                if (cleanDate.length != 8) {
                    return false
                }
            } catch (e: Exception) {
                return false
            }
            return true
        }

        fun String.validatePhone(): Boolean {
            try {
                val cleanPhone = this.replace("-", "")
                    .replace("(", "").replace(")", "")

                if (cleanPhone.length != 10) {
                    return false
                }
                return true
            } catch (e: Exception) {
                return false
            }
        }
    }
}