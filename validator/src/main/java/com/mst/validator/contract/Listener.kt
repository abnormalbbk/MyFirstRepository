package com.mst.validator.contract

import android.view.View
import com.mst.validator.ValidationConfig

interface Listener {
    interface OnItemSelectListener {
        fun onItemSelected(position: Int) {}
    }

    interface OnValidationListener {
        fun onValidated()
        fun onError()
    }

    interface Validator {
        fun validate()
        fun validate(async: Boolean)
        fun setCustomError(tag: String, error: String)
        fun setCustomError(errorMap: Map<String, String>)
        fun removeValidation(view: View)
        fun addValidation(config: ValidationConfig)
        fun getSpinnerListener(tag: String): OnItemSelectListener?
        fun isValidation(): Boolean
    }
}