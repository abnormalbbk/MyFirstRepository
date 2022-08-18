package com.mst.validator.rules

import android.content.Context
import android.view.View
import com.google.android.material.textfield.TextInputEditText
import com.mobsandgeeks.saripaar.QuickRule
import com.mst.validator.ValidationErrorMessage
import com.mst.validator.utils.ValidatorEmptyUtil


class Password() : QuickRule<View>() {
    private var error: String? = null
    private val schemePatterns: Map<Scheme, String> = object : HashMap<Scheme, String>() {
        init {
            put(Scheme.ANY, ".+")
            put(Scheme.ALPHA, "\\w+")
            put(Scheme.ALPHA_MIXED_CASE, "(?=.*[a-z])(?=.*[A-Z]).+")
            put(Scheme.NUMERIC, "\\d+")
            put(Scheme.ALPHA_NUMERIC, "(?=.*[a-zA-Z])(?=.*[\\d]).+")
            put(
                Scheme.ALPHA_NUMERIC_MIXED_CASE,
                "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d]).+"
            )
            put(
                Scheme.ALPHA_NUMERIC_SYMBOLS,
                "(?=.*[a-zA-Z])(?=.*[\\d])(?=.*([^\\w])).+"
            )
            put(
                Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS,
                "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*([^\\w])).+"
            )
        }
    }
    private var minLength = 8
    private var scheme = Scheme.ANY

    constructor(minLength: Int) : this() {
        this.minLength = minLength
    }

    constructor(minLength: Int, scheme: Scheme) : this() {
        this.minLength = minLength
        this.scheme = scheme
    }

    override fun isValid(view: View?): Boolean {
        if (ValidatorEmptyUtil.isNotNull(view) && view is TextInputEditText) {
            val password = view.text.toString()
            if (password.isNotEmpty()) {
                val hasMinChars = password.length >= minLength
                val pattern = schemePatterns[scheme] ?: ""
                val matchesScheme = password.matches(Regex.fromLiteral(pattern))
                error = if (hasMinChars) {
                    ValidationErrorMessage.PASSWORD_ERROR
                } else {
                    ValidationErrorMessage.PASSWORD_LENGTH_ERROR.replace(
                        "{}",
                        minLength.toString()
                    )
                }
                return hasMinChars && matchesScheme
            }
        }
        return true
    }

    override fun getMessage(context: Context?): String? {
        return error
    }

    enum class Scheme {
        ANY, ALPHA, ALPHA_MIXED_CASE, NUMERIC, ALPHA_NUMERIC, ALPHA_NUMERIC_MIXED_CASE, ALPHA_NUMERIC_SYMBOLS, ALPHA_NUMERIC_MIXED_CASE_SYMBOLS
    }
}