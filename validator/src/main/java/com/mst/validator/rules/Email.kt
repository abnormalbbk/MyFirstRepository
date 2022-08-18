package com.mst.validator.rules

import android.content.Context
import android.view.View
import com.google.android.material.textfield.TextInputLayout
import com.mobsandgeeks.saripaar.QuickRule
import com.mst.validator.ValidationErrorMessage
import com.mst.validator.utils.ValidatorEmptyUtil
import commons.validator.routines.EmailValidator

class Email : QuickRule<View>() {
    override fun isValid(view: View?): Boolean {
        if (ValidatorEmptyUtil.isNotNull(view) && view is TextInputLayout) {
            return EmailValidator.getInstance().isValid(view.editText?.text?.toString())
        }
        return true
    }

    override fun getMessage(context: Context?): String {
        return ValidationErrorMessage.EMAIL_ERROR
    }
}