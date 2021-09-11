package io.github.pavel.jetpack.extension

class StringExtension {

    fun String.capitalizeFirstChar(): String {
        return replaceFirstChar {
            it.uppercaseChar()
        }
    }
}