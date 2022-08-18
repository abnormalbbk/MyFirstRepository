package com.mst.validator

class ValidationErrorMessage {
    @Suppress("unused")
    companion object {
        const val MOBILE_ERROR = "Enter valid mobile number"
        const val EMAIL_ERROR = "Enter valid email address"
        const val MOBILE_EMAIL_ERROR = "Enter valid mobile number or email address"
        const val EMPTY_ERROR = "This field is required"
        const val FULL_NAME_ERROR = "Enter full name"
        const val PASSWORD_ERROR = "Invalid password"
        const val PASSWORD_LENGTH_ERROR = "Password should be of minimum {} characters"
        const val PASSWORD_CONFIRM_ERROR = "Password does not match"
        const val PIN_ERROR = "Invalid PIN"
        const val PIN_CONFIRM_ERROR = "PIN does not match"
        const val DATE_ERROR = "Enter valid date"
        const val RANGE_LOW_ERROR = "Amount should be greater than or equal to "
        const val RANGE_HIGH_ERROR = "Amount should be less than or equal to "
        const val NUMBER_ERROR = "Invalid Number"
    }
}