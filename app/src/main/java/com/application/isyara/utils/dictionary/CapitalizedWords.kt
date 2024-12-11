package com.application.isyara.utils.dictionary

import java.util.Locale

fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it ->
    it.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    }
}