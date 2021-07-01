package ru.skillbranch.devintensive.extensions

fun String.truncate(symbolsCount:Int = 16) :String {
    if (this.length < symbolsCount)
        return this.trimEnd()

    val string1 = this.substring(0, symbolsCount)
    val string2 = this.substring(symbolsCount)

    return when{
        string2.isNullOrBlank() -> string1.trimEnd()
        else -> "${string1.trimEnd()}..."
    }
}