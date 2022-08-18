package com.mst.validator.rules

import android.content.Context
import android.view.View
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.mobsandgeeks.saripaar.QuickRule
import com.mst.validator.ValidationErrorMessage
import com.mst.validator.utils.ValidatorEmptyUtil

class PasswordConfirm(private val passwordView: TextInputEditText) :
    QuickRule<View>() {

    override fun isValid(view: View?): Boolean {
        if (view is TextInputLayout?) {
            val text = view?.editText?.text?.toString() ?: ""
            return ValidatorEmptyUtil.isEmpty(text) || text.equals(
                passwordView.text?.toString() ?: "",
                ignoreCase = true
            )
        }
        return false
    }

    override fun getMessage(context: Context?): String {
        return ValidationErrorMessage.PASSWORD_CONFIRM_ERROR
    }
}