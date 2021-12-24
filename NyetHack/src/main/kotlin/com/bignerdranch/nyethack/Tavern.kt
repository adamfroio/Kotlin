package com.bignerdranch.nyethack
import java.io.File
import kotlin.random.Random
import kotlin.random.nextInt


private const val TAVERN_MASTER = "Taernyl"
private const val TAVERN_NAME = "$TAVERN_MASTER's Folly"

private val firstNames = setOf("Alex", "Mordoc", "Sophie", "Tariq")
private val lastNames = setOf("Ironfoot", "Fernsworth", "Baggins", "Downstrider")

private val menuData = File("NyetHack/data/tavern-menu-data.txt")   // Surface
//private val menuData = File("data/tavern-menu-data.txt")      // Alienware
    .readText()
    .split("\n")
    .map { it.split(",") }

private val menuItems = menuData.map { (_, name, _) -> name }

private val menuItemPrices = menuData.associate { (_, name, price) ->
    name to price.toDouble()
}

private val menuItemTypes = menuData.associate { (type, name, _)  ->
        name to type
}


class Tavern : Room(TAVERN_NAME) {

    val patrons: MutableSet<String> = firstNames.shuffled()
        .zip(lastNames.shuffled()) { firstName, lastName -> "$firstName $lastName"}
        .toMutableSet()

    val patronGold = mutableMapOf(
        TAVERN_MASTER to 86.0,
        player.name to 4.50,
        *patrons.map { it to 6.00 }.toTypedArray()
    )

    override val status = "Busy"

    val itemOfDay = patrons.flatMap { getFavoriteMenuItems(it) }.random()

    override fun enterRoom() {
        narrate("${player.name} enters $TAVERN_NAME")
        narrate("There are several items for sale:")

        narrate(menuItems.joinToString())
        narrate("The item of the day is the $itemOfDay")

        narrate("${player.name} sees several patrons in the tavern:")
        narrate(patrons.joinToString())

        placeOrder(patrons.random(), menuItems.random())
    }

    private fun placeOrder(
        patronName: String,
        menuItemName: String
    ) {
        val itemPrice = menuItemPrices.getValue(menuItemName)
        narrate("$patronName places an order with $TAVERN_MASTER.")
        if (itemPrice <= patronGold.getOrDefault(patronName, 0.0)) {
            val action = when (menuItemTypes[menuItemName]) {
                "shandy", "elixir" -> "pours"
                "meal" -> "serves"
                else -> "hands"
            }
            narrate("$TAVERN_MASTER $action $patronName a $menuItemName")
            narrate("$patronName pays $TAVERN_MASTER $itemPrice gold")
            patronGold[patronName] = patronGold.getValue(patronName) - itemPrice
            patronGold[TAVERN_MASTER] = patronGold.getValue(TAVERN_MASTER) + itemPrice
        } else {
            narrate("$TAVERN_MASTER says, \"You need more coin for a $menuItemName\"")
        }

    }
}


private fun getFavoriteMenuItems(patron: String): List<String> {
    return when (patron) {
        "Alex Ironfoot" -> menuItems.filter { menuItem ->
            menuItemTypes[menuItem]?.contains("dessert") == true
        }
        else -> menuItems.shuffled().take(Random.nextInt(1..2))
    }
}

