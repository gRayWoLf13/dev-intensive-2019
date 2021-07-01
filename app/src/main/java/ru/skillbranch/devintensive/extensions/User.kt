package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.utils.Utils
import java.time.Duration
import java.util.*

fun User.toUserView():UserView{

    val nickName = Utils.transliteration("$firstName $lastName")
    val initials = Utils.toInitials(firstName,  lastName)
    val status = if (lastVisit == null) "Еще ни разу не был" else if (isOnline) "Онлайн" else "Последний раз был ${lastVisit!!.humanizeDiff()}"

    return UserView(
        id,
        fullName = "$firstName $lastName",
        nickName = nickName,
        initials = initials,
        avatar = avatar,
        status = status)
}

fun Date.humanizeDiff(): String {
    var currentDate = Date()
    var diff: Long = currentDate.time - this.time
    val isNegative = diff < 0
    if (diff < 0)
        diff = -diff;
    val seconds = diff / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24

    return when{
        seconds in 0..1 -> "только что"
        seconds in 1..45 -> {if (isNegative) "через несколько секунд" else "несколько секунд назад"}
        seconds in 45..75 -> {if (isNegative) "через несколько минут" else "минуту назад"}
        seconds > 75 && minutes < 45 -> {if (isNegative) "через ${humanizeTime(minutes, TimeUnits.MINUTE)}" else "${humanizeTime(minutes, TimeUnits.MINUTE)} назад"}
        minutes in 45..75 -> {if (isNegative) "через час" else "час назад"}
        minutes > 75 && hours < 22 -> {if (isNegative) "через ${humanizeTime(hours, TimeUnits.HOUR)}" else "${humanizeTime(hours, TimeUnits.HOUR)} назад"}
        hours in 22..26 -> {if (isNegative) "через день" else "день назад"}
        hours > 26 && days < 360 -> {if (isNegative) "через ${humanizeTime(days, TimeUnits.DAY)}" else "${humanizeTime(days, TimeUnits.DAY)} назад"}
        days > 360 -> {if (isNegative) "более чем через год" else "более года назад"}
        else -> "ошибка"
    }
}

fun humanizeTime(value:Long, units: TimeUnits):String{
    val lastDigit = value % 10
    val lastTwoDigits = value % 100
    return when{
        (lastDigit == 0L || lastDigit > 4 || lastTwoDigits in 10..20) -> when(units){
            TimeUnits.SECOND -> "$value секунд"
            TimeUnits.MINUTE -> "$value минут"
            TimeUnits.HOUR -> "$value часов"
            TimeUnits.DAY -> "$value дней"
        }

        lastDigit == 1L -> when(units){
            TimeUnits.SECOND -> "$value секунду"
            TimeUnits.MINUTE -> "$value минуту"
            TimeUnits.HOUR -> "$value час"
            TimeUnits.DAY -> "$value день"
        }

        lastDigit in 2..4 -> when(units){
            TimeUnits.SECOND -> "$value секунды"
            TimeUnits.MINUTE -> "$value минуты"
            TimeUnits.HOUR -> "$value часа"
            TimeUnits.DAY -> "$value дня"
        }
        else -> "оишбка"
    }
}
