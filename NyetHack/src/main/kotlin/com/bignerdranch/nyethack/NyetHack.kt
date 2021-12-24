package com.bignerdranch.nyethack

lateinit var player: Player

fun main() {

    narrate("Welcome to Nyethack!")
    val playerName = promptHeroName()
    player = Player(playerName)
    player.prophesize()

    var currentRoom: Room = TownSquare()
    val mortality = if (player.isImmortal) "an immortal" else "a mortal"
    narrate("${player.name}, of ${player.hometown}, ${player.title}, is in ${currentRoom.description()}")
    narrate("${player.name}, $mortality, has ${player.healthPoints} health points")
    currentRoom.enterRoom()

    player.castFireball()
    player.prophesize()
}

private fun promptHeroName(): String {
    narrate("A hero enters the town of Kronstadt.  What is their name?") { message ->
        "\u001B[33;1m$message\u001b[0m"
    }
        println("Madrigal")
        return "Madrigal"
}

