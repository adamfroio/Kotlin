
package com.bignerdranch.nyethack

open class Room(val name: String) {

    protected open val status = "calm"
    fun description() = "$name (Currently: $status)"

    open fun enterRoom() {
        narrate("There is nothing to do here")
    }
}
