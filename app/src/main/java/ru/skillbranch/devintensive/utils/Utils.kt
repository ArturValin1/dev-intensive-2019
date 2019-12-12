package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")
        val firstName =
            if (parts?.getOrNull(0).isNullOrEmpty()) null else parts?.getOrNull(0)
        val lastName =
            if (parts?.getOrNull(1).isNullOrEmpty()) null else parts?.getOrNull(1)
        return Pair(firstName, lastName)
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val map: MutableMap<Char, String> = mutableMapOf(
            'а' to "a",
            'б' to "b",
            'в' to "v",
            'г' to "g",
            'д' to "d",
            'е' to "e",
            'ё' to "e",
            'ж' to "zh",
            'з' to "z",
            'и' to "i",
            'й' to "i",
            'к' to "k",
            'л' to "l",
            'м' to "m",
            'н' to "n",
            'о' to "o",
            'п' to "p",
            'р' to "r",
            'с' to "s",
            'т' to "t",
            'у' to "u",
            'ф' to "f",
            'х' to "h",
            'ц' to "c",
            'ч' to "ch",
            'ш' to "sh",
            'щ' to "sh'",
            'ъ' to "",
            'ы' to "i",
            'ь' to "",
            'э' to "e",
            'ю' to "yu",
            'я' to "ya",
            ' ' to " "
        )
        var trans: String =
            buildString {
                for (index in payload) {
                    if(map.containsKey(index.toLowerCase()))
                    append(map.get(index.toLowerCase()))
                    else append(index)
                }
            }
        var (first, second) = parseFullName(trans)
        val firstChar: Char? = first?.get(0)!!.toUpperCase()
        val secondChar: Char? = second?.get(0)!!.toUpperCase()
        val newFirst: String? = firstChar.toString() + first.drop(1)
        val newSecond: String? = secondChar.toString() + second.drop(1)
        val result = buildString {
            append(newFirst)
            append(divider)
            append(newSecond)
        }
        return result
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val firstInitial = if (firstName?.length!! > 0) firstName?.get(0) else null
        val secondInitial = if (lastName?.length!! > 0) lastName?.get(0) else null
        var result: String = buildString {
            if (firstInitial != null && firstInitial != ' ') {
                append(firstInitial)
                if (secondInitial != null && secondInitial != ' ') {
                    append(secondInitial)
                }
            }
        }
        return if (result.length < 1) null else result.toUpperCase()
    }
}