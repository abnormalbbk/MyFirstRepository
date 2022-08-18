package com.mst.validator.rules

import android.content.Context
import android.view.View
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.textfield.TextInputLayout
import com.mobsandgeeks.saripaar.QuickRule
import com.mst.validator.ValidationErrorMessage
import com.mst.validator.utils.ValidatorEmptyUtil


class NotEmpty : QuickRule<View>() {
    override fun isValid(view: View?): Boolean {
        if (ValidatorEmptyUtil.isNotNull(view)) {
            return when (view) {
                is TextInputLayout -> {
                    (view.editText?.text?.toString() ?: "").isNotEmpty()
                }
                is AppCompatEditText -> {
                    (view.text?.toString() ?: "").isNotEmpty()
                }
                is Spinner -> {
                    view.selectedItemPosition != 0
                }
                is AppCompatTextView -> {
                    (view.text?.toString() ?: "").isNotEmpty()
                }
                else -> true
            }
        }
        return true
    }

    override fun getMessage(context: Context?): String {
        return ValidationErrorMessage.EMPTY_ERROR
    }
}