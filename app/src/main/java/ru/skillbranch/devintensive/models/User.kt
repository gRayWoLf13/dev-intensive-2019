package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User (
    var id:String,
    var firstName:String?,
    var lastName:String?,
    var avatar:String?,
    var rating:Int = 0,
    var respect:Int = 0,
    var lastVisit:Date? = null,
    var isOnline:Boolean = false
){

    var introBit:String

    constructor(id:String, firstName: String?, lastName: String?) :
            this(id, firstName, lastName, avatar = null)

    constructor(id: String) :
            this(id, firstName = "John", lastName = "Doe")

    init {

        introBit = getIntro()

        println("It's alive! \n" +
                "${if(lastName === "Doe") "His name is $firstName $lastName" else "And his name is $firstName $lastName"}\n")
    }

    private fun getIntro()="""
        to tu tdlkfgj fdg
        dfggfg fdgdg fgjfg
        
        $firstName $lastName
    """.trimIndent()

    fun printMe() = println("""
                id $id
                firstName $firstName
                lastName $lastName
                avatar $avatar
                rating $rating
                respect $respect
                lastVisit $lastVisit
                isOnline $isOnline       
        """.trimIndent())

    class Builder(
        private val user: User = User("-1")
    ) {
        fun id(id: String): Builder {
            user.id = id
            return this
        }

        fun firstName(firstName: String?): Builder {
            user.firstName = firstName
            return this
        }

        fun lastName(lastName: String?): Builder {
            user.lastName = lastName
            return this
        }

        fun avatar(avatar: String?): Builder {
            user.avatar = avatar
            return this
        }

        fun rating(rating: Int): Builder {
            user.rating = rating
            return this
        }

        fun respect(respect: Int): Builder {
            user.respect = respect
            return this
        }

        fun lastVisit(lastVisit: Date?): Builder {
            user.lastVisit = lastVisit
            return this
        }

        fun isOnline(isOnline: Boolean): Builder {
            user.isOnline = isOnline
            return this
        }

        fun build(): User = user
    }

    companion object Factory{
        private var lastId = -1

        fun makeUser(fullName:String?) : User{
            lastId++

            val (firstName, lastName) = Utils.parseFullName(fullName)

            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }
}