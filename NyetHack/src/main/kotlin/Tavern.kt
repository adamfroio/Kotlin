
import java.io.File


private const val TAVERN_MASTER = "Taernyl"
private const val TAVERN_NAME = "$TAVERN_MASTER's Folly"

private val firstNames = setOf("Alex", "Mordoc", "Sophie", "Tariq")
private val lastNames = setOf("Ironfoot", "Fernsworth", "Baggins", "Downstrider")

private val menuData = File("NyetHack/data/tavern-menu-data.txt")
    .readText()
    .split("\n")

private val menuItems = List(menuData.size) { index ->
    val (_, name, _) = menuData[index].split(",")
    name
}

private val menuPrices = List(menuData.size) { index ->
    val (_, _, price) = menuData[index].split(",")
    price
}


fun visitTavern () {
    narrate("$heroName enters $TAVERN_NAME")
    narrate("There are several items for sale:")

    //Chapter 8 Challenge 1
    var longest: Int = menuItems[0].length

    // get longest menu item
    menuItems.forEachIndexed { index, item ->
        if (item.length > longest) { longest = item.length }
    }

    narrate(menuItems.joinToString())

    val patrons: MutableSet<String> = mutableSetOf()
    while (patrons.size < 10) {
        patrons += "${firstNames.random()} ${lastNames.random()}"
    }

    narrate("$heroName sees several patrons in the tavern:")
    narrate(patrons.joinToString())

    repeat(patrons.size) {
        placeOrder(patrons.random(), menuItems.random())
    }

}


private fun placeOrder(patronName: String, menuItemName: String) {
    narrate("$patronName places an order with $TAVERN_MASTER.")
    narrate("$TAVERN_MASTER hands $patronName a plate of $menuItemName.")
}


