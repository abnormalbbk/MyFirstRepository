package com.mst.validator.utils

import android.util.SparseArray
import android.util.SparseBooleanArray
import android.util.SparseIntArray
import android.util.SparseLongArray

class ValidatorEmptyUtil {
    companion object {
        fun isEmpty(obj: Any?): Boolean {
            if (obj == null) {
                return true
            }
            if (obj is String && obj.toString().isEmpty()) {
                return true
            }
            if (obj.javaClass.isArray && (obj as Array<*>).isEmpty()) {
                return true
            }
            if (obj is Collection<*> && obj.isEmpty()) {
                return true
            }
            if (obj is Map<*, *> && obj.isEmpty()) {
                return true
            }
            if (obj is SparseArray<*> && obj.size() == 0) {
                return true
            }
            if (obj is SparseBooleanArray && obj.size() == 0) {
                return true
            }
            if (obj is SparseIntArray && obj.size() == 0) {
                return true
            }
            if (obj is SparseLongArray && obj.size() == 0) {
                return true
            }
            return false
        }

        fun isNotEmpty(obj: Any?): Boolean {
            return !isEmpty(obj)
        }

        fun isNull(obj: Any?): Boolean {
            return obj == null
        }

        fun isNotNull(obj: Any?): Boolean {
            return !isNull(obj)
        }
    }
}