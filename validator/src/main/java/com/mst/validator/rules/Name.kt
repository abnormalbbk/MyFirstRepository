package com.mst.validator.rules

//import com.mst.validator
import android.content.Context
import android.view.View
import com.google.android.material.textfield.TextInputLayout
import com.mobsandgeeks.saripaar.QuickRule
import com.mst.validator.ValidationErrorMessage
import com.mst.validator.utils.ValidatorEmptyUtil

@Suppress("UNUSED_VARIABLE")
class Name : QuickRule<View>() {
    override fun isValid(view: View?): Boolean {
        if (ValidatorEmptyUtil.isNotNull(view) && view is TextInputLayout) {
            val name = view.editText?.text.toString()
            // NAME NOT VALIDATED
        }
        return true
    }

    override fun getMessage(context: Context?): String {
        return ValidationErrorMessage.FULL_NAME_ERROR
    }
}