package com.bignerdranch.nyethack
class Player(
    initialName: String,
    val hometown: String = "Neversummer",
    var healthPoints: Int,
    val isImmortal: Boolean
) {

    constructor(name: String) : this(
        initialName = name,
        healthPoints = 100,
        isImmortal = false
    ) {
        if (name.equals("Jason", ignoreCase = true)) {
            healthPoints = 500
        }
    }


    var name = initialName
    get() = field.replaceFirstChar { it.uppercase() }
    private set(value) {
        field = value.trim()
    }


    init {
        require(healthPoints > 0) { "HealthPoints must be greater than zero" }
        require(name.isNotBlank()) { "Player must have a name" }
    }



    val title: String
    get() = when {
        name.all { it.isDigit() } -> "The Identifiable"
        name.all { it.isLetter() } -> "The Witness Protection Member"
        name.count { it.lowercase() in "aeiou" } > 4 -> "The Master of Vowels"
        else -> "The Renowned"
    }

    fun castFireball(numFireballs: Int = 2) {
        narrate("A glass of Fireball springs into existence (x$numFireballs)")
    }

    fun changeName(newName: String) {
        narrate("$name legally changes their name to $newName")
        name = newName
    }

}