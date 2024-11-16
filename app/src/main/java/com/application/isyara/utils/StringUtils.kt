package com.application.isyara.utils

import android.util.Patterns

fun String.isEmailValid(): Boolean {
    return this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

