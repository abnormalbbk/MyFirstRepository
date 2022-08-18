package com.mst.validator

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.textfield.TextInputLayout
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.Validator.ValidationListener
import com.mst.validator.contract.Listener
import com.mst.validator.contract.Listener.OnValidationListener
import com.mst.validator.utils.ValidatorEmptyUtil


@Suppress("unused")
class Validator(
    val context: Context?,
    validations: List<ValidationConfig>,
    onValidationListener: OnValidationListener
) {
    private var validator: Validator? = null
    private var editableMap: MutableMap<String, View>? = null
    private var spinnerMap: MutableMap<String, Listener.OnItemSelectListener>? = null

    init {
        init(validations, onValidationListener)
    }

    fun validate() {
        validator?.validate(true)
    }

    fun validate(async: Boolean) {
        validator!!.validate(async)
    }

    fun setCustomError(viewTag: String, error: String) {
        if (editableMap!!.containsKey(viewTag)) {
            setError(editableMap!![viewTag] as AppCompatEditText?, error)
        }
    }

    fun setCustomError(errorMap: Map<String, String>) {
        for (key in errorMap.keys) {
            setCustomError(key, errorMap[key] ?: "")
        }
    }

    fun removeValidation(view: View) {
        if (ValidatorEmptyUtil.isNotNull(validator)) {
            validator?.removeRules(view)
            editableMap?.minus(view.tag?.toString())
        }
    }

    fun addValidation(validationConfig: ValidationConfig) {
        if (ValidatorEmptyUtil.isNotNull(validator)) {
            onPutValidation(validationConfig)
        }
    }

    fun getSpinnerListener(tag: String?): Listener.OnItemSelectListener? {
        if (ValidatorEmptyUtil.isNotNull(spinnerMap) && ValidatorEmptyUtil.isNotNull(tag)) {
            return spinnerMap!![tag!!]
        }
        return null
    }

    fun isValidating(): Boolean {
        return validator!!.isValidating
    }

    private fun init(
        validations: List<ValidationConfig>,
        onValidationListener: OnValidationListener
    ) {
        editableMap = HashMap()
        validator = Validator(this)
        for (config in validations) {
            onPutValidation(config)
        }
        validator!!.setValidationListener(object : ValidationListener {
            override fun onValidationSucceeded() {
                onValidationListener.onValidated()
            }

            override fun onValidationFailed(errors: List<ValidationError>) {
                onValidationListener.onError()
                for (validationError in errors) {
                    val view: View = validationError.view
                    // Currently showing single message only
                    // TODO: Bibek Need to set an option to show failed rule message or multiple
//                    val msg =
//                        validationError.getCollatedErrorMessage(context).split("\\r\\n|\\r|\\n")
//                            .toTypedArray()
//                    val message = msg.first()
                    val message = validationError.failedRules?.first()?.getMessage(context)
                    if (view is TextInputLayout) {
                        setError(view, message)
                    } else if (view is AppCompatEditText) {
                        setError(view, message)
                    } else if (view is AppCompatTextView || view is Spinner) {
                        val v: View? = editableMap!![view.tag?.toString()]
                        if (ValidatorEmptyUtil.isNotNull(v) && v is AppCompatEditText) {
                            val et = v as AppCompatEditText?
                            val oldTag = et!!.tag?.toString()
                            et.tag = "error_view"
                            setError(et, message)
                            et.tag = oldTag
                        } else {
                            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    private fun setError(view: AppCompatEditText?, error: String?) {
        view!!.visibility = View.VISIBLE
        // TODO:  Implement animation if needed
//        val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.shake)
//        view.startAnimation(animation)
        view.error = error
//
    }

    private fun setError(view: TextInputLayout?, error: String?) {
        view!!.visibility = View.VISIBLE
        // TODO:  Implement animation if needed
//        val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.shake)
//        view.startAnimation(animation)
        view.isErrorEnabled = true
        view.error = error
    }

    private fun onPutValidation(config: ValidationConfig) {
        val extra = config.getExtra()
        val rules = config.getQuickRule()
        val tag = config.getViewTag() ?: ""
        val errorView = config.getErrorView()
        val view = config.getView()

        if (rules.isNotEmpty() && ValidatorEmptyUtil.isNotNull(view)) {
            for (rule in config.getQuickRule()) {
                validator!!.put(view, rule)
            }

            if (tag.isNotEmpty()) {
                if (ValidatorEmptyUtil.isNotNull(errorView)) {
                    config.getErrorView()!!.tag = tag
                }
                config.getView()!!.tag = tag
                editableMap!![tag] = errorView ?: view!!

                if (config.getView() is AppCompatSpinner) {
                    var customListener = false
                    if (ValidatorEmptyUtil.isNotNull(extra)) {
                        customListener = extra!!.customListener
                    }
                    if (customListener) {
                        spinnerMap!![tag] = object : Listener.OnItemSelectListener {
                            override fun onItemSelected(position: Int) {
                                if (position != 0) {
                                    val et =
                                        editableMap!![tag] as TextInputLayout?
                                    if (ValidatorEmptyUtil.isNotNull(et)) {
                                        et!!.tag = "error_view"
                                        setError(et, null)
                                        et.tag = tag
                                    }
                                }
                            }
                        }
                    } else {
                        (view as AppCompatSpinner?)!!.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    adapterView: AdapterView<*>,
                                    view: View?,
                                    i: Int,
                                    l: Long
                                ) {
                                    if (adapterView.selectedItemPosition != 0) {
                                        val et =
                                            editableMap!![tag] as TextInputLayout?
                                        if (ValidatorEmptyUtil.isNotNull(et)) {
                                            et!!.tag = "error_view"
                                            setError(et, null)
                                            et.tag = tag
                                        }
                                    }
                                }

                                override fun onNothingSelected(adapterView: AdapterView<*>?) {}
                            }
                    }
                }
                if (view is AppCompatTextView) {
                    (view as AppCompatTextView?)!!.addTextChangedListener(object :
                        TextWatcher {
                        override fun beforeTextChanged(
                            charSequence: CharSequence,
                            i: Int,
                            i1: Int,
                            i2: Int
                        ) {
                        }

                        override fun onTextChanged(
                            charSequence: CharSequence,
                            i: Int,
                            i1: Int,
                            i2: Int
                        ) {
                            val et = editableMap!![tag] as TextInputLayout?
                            if (ValidatorEmptyUtil.isNotNull(et)) {
                                et!!.tag = "error_view"
                                setError(et, null)
                                et.tag = tag
                            }
                        }

                        override fun afterTextChanged(editable: Editable) {}
                    })
                }
            }
        }
    }
}