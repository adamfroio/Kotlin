package com.bignerdranch.nyethack

interface Fightable {
    val name: String
    var healthPoints: Int
    var diceCount: Int
    var diceSides: Int

    fun takeDamage(damage: Int)

    fun attack(opponent: Fightable)

}