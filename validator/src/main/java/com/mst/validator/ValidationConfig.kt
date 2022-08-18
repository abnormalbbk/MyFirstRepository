package com.mst.validator

import android.view.View
import com.google.android.material.textfield.TextInputEditText
import com.mobsandgeeks.saripaar.QuickRule


@Suppress("unused")
class ValidationConfig {
    private var quickRule: Array<QuickRule<View>>
    private var view: View? = null
    private var errorView: TextInputEditText? = null
    private var viewTag: String? = null
    private var extra: Extra? = null


    constructor (view: View?, vararg quickRule: QuickRule<View>) {
        this.quickRule = quickRule.toList().toTypedArray()
        this.view = view
    }

    constructor(view: View?, viewTag: String, vararg quickRule: QuickRule<View>) {
        this.quickRule = quickRule.toList().toTypedArray()
        this.view = view
        this.viewTag = viewTag
    }

    constructor(
        view: View?,
        errorView: TextInputEditText?,
        vararg quickRule: QuickRule<View>
    ) {
        this.quickRule = quickRule.toList().toTypedArray()
        this.view = view
        this.errorView = errorView
    }

    fun constructor(
        view: View?,
        errorView: TextInputEditText?,
        viewTag: String,
        vararg quickRule: QuickRule<View>
    ) {
        this.quickRule = quickRule.toList().toTypedArray()
        this.view = view
        this.viewTag = viewTag
        this.errorView = errorView
    }

    constructor(view: View?, extra: Extra?, vararg quickRule: QuickRule<View>) {
        this.quickRule = quickRule.toList().toTypedArray()
        this.view = view
        this.extra = extra
    }

    constructor(
        view: View?,
        viewTag: String,
        extra: Extra?,
        vararg quickRule: QuickRule<View>
    ) {
        this.quickRule = quickRule.toList().toTypedArray()
        this.view = view
        this.viewTag = viewTag
        this.extra = extra
    }

    constructor(
        view: View?,
        errorView: TextInputEditText?,
        extra: Extra?,
        vararg quickRule: QuickRule<View>
    ) {
        this.quickRule = quickRule.toList().toTypedArray()
        this.view = view
        this.errorView = errorView
        this.extra = extra
    }

    constructor(
        view: View?,
        errorView: TextInputEditText?,
        viewTag: String,
        extra: Extra?,
        vararg quickRule: QuickRule<View>
    ) {
        this.quickRule = quickRule.toList().toTypedArray()
        this.view = view
        this.viewTag = viewTag
        this.errorView = errorView
        this.extra = extra
    }

    fun getView(): View? {
        return view
    }

    fun getQuickRule(): Array<QuickRule<View>> {
        return quickRule
    }

    fun getViewTag(): String? {
        return viewTag
    }

    fun getErrorView(): TextInputEditText? {
        return errorView
    }

    fun getExtra(): Extra? {
        return extra
    }
}