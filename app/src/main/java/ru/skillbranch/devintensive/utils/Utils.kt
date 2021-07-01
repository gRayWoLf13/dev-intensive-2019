package ru.skillbranch.devintensive.utils

import ru.skillbranch.devintensive.models.User

object Utils {
    fun parseFullName(fullName:String?):Pair<String?, String?>{
        val parts : List<String>? = fullName?.split(" ")

        var firstName = parts?.getOrNull(0)
        if (firstName?.isBlank() != false)
            firstName = null
        var lastName = parts?.getOrNull(1)
        if (lastName?.isBlank() != false)
            lastName = null

        //return Pair(firstName, lastName)
        //the same code as:
        return  firstName to lastName
    }

    private val dict = mapOf(
        "а" to "a",
        "б" to "b",
        "в" to "v",
        "г" to "g",
        "д" to "d",
        "е" to "e",
        "ё" to "e",
        "ж" to "zh",
        "з" to "z",
        "и" to "i",
        "й" to "i",
        "к" to "k",
        "л" to "l",
        "м" to "m",
        "н" to "n",
        "о" to "o",
        "п" to "p",
        "р" to "r",
        "с" to "s",
        "т" to "t",
        "у" to "u",
        "ф" to "f",
        "х" to "h",
        "ц" to "c",
        "ч" to "ch",
        "ш" to "sh",
        "щ" to "sh'",
        "ъ" to "",
        "ы" to "i",
        "ь" to "",
        "э" to "e",
        "ю" to "yu",
        "я" to "ya",

        "А" to "A",
        "Б" to "B",
        "В" to "V",
        "Г" to "G",
        "Д" to "D",
        "Е" to "E",
        "Ё" to "E",
        "Ж" to "Zh",
        "З" to "Z",
        "И" to "I",
        "Й" to "I",
        "К" to "K",
        "Л" to "L",
        "М" to "M",
        "Н" to "N",
        "О" to "O",
        "П" to "P",
        "Р" to "R",
        "С" to "S",
        "Т" to "T",
        "У" to "U",
        "Ф" to "F",
        "Х" to "H",
        "Ц" to "C",
        "Ч" to "Ch",
        "Ш" to "Sh",
        "Щ" to "Sh'",
        "Ъ" to "",
        "Ы" to "I",
        "Ь" to "",
        "Э" to "E",
        "Ю" to "Yu",
        "Я" to "Ya",
    )

    fun transliteration(payload: String, divider:String = " "): String {
        var result = payload.replace(" ", divider)
        for(char in dict) {
            result = result.replace(char.key, char.value)
        }

        return result
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val firstInitial = when{
            firstName.isNullOrBlank() -> null
            else -> firstName[0].uppercase()
        }
        val lastInitial = when{
            lastName.isNullOrBlank() -> null
            else -> lastName[0].uppercase()
        }
        return when{
            firstInitial == null && lastInitial == null -> null
            firstInitial == null -> lastInitial
            lastInitial == null -> firstInitial
            else -> "$firstInitial$lastInitial"
        }
    }
}
