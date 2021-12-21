
import java.io.File


private const val TAVERN_MASTER = "Taernyl"
private const val TAVERN_NAME = "$TAVERN_MASTER's Folly"

private val firstNames = setOf("Alex", "Mordoc", "Sophie", "Tariq")
private val lastNames = setOf("Ironfoot", "Fernsworth", "Baggins", "Downstrider")

private val menuData = File("NyetHack/data/tavern-menu-data.txt")   // Surface
//private val menuData = File("data/tavern-menu-data.txt")  // Alienware
    .readText()
    .split("\n")

private val menuItems = List(menuData.size) { index ->
    val (_, name, _) = menuData[index].split(",")
    name
}

private val menuItemPrices: Map<String, Double> = List(menuData.size) { index ->
    val (_, name, price) = menuData[index].split(",")
    name to price.toDouble()
}.toMap()

private val menuItemTypes: Map<String, String> = List(menuData.size) { index ->
    val (type, name, _) = menuData[index].split(",")
    name to type
}.toMap()


fun visitTavern () {
    narrate("$heroName enters $TAVERN_NAME")
    narrate("There are several items for sale:")

     narrate(menuItems.joinToString())

    val patrons: MutableSet<String> = mutableSetOf()
    val patronGold = mutableMapOf(
        TAVERN_MASTER to 86.0,
        heroName to 4.50
    )

    while (patrons.size < 5) {
        val patronName = "${firstNames.random()} ${lastNames.random()}"
        patrons += patronName
        patronGold += patronName to 6.0
    }

    narrate("$heroName sees several patrons in the tavern:")
    narrate(patrons.joinToString())

    repeat(3) {
        placeOrder(patrons.random(), menuItems.random(), patronGold)
    }

//    printMenu()

    displayPatronBalances(patronGold)
}


private fun placeOrder(
    patronName: String,
    menuItemName: String,
    patronGold: MutableMap<String, Double>
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

private fun displayPatronBalances(patronGold: Map<String, Double>) {
    narrate("$heroName intuitively knows how much money each patron has")
    patronGold.forEach { (patron, balance) ->
        narrate("$patron has ${"%.2f".format(balance)} gold")
    }
}



// Chapter 9 Challenges
fun printMenu() {

    var longestItem = 0
    var numItems = 0
    var longestLine = 0

    val menuPrices = List(menuData.size) { index ->
        val (_, _, price) = menuData[index].split(",")
        price
    }

    val menuTypes = List(menuData.size) { index ->
        val (type, _, _) = menuData[index].split(",")
        type
    }


    // get longest menu item
    menuItems.forEach { item ->
        if (item.length > longestItem) {
            longestItem = item.length
        }
        numItems++
    }

    // get longest menu line (item + price)
    var i = 0
    while (i < numItems) {
        if (menuItems[i].length + menuPrices[i].length - 1 > longestLine) {
            longestLine = menuItems[i].length + menuPrices[i].length
        }
        i++
    }

    println("\r\n*** Welcome to $TAVERN_NAME ***")
    i = 0
    var menuType = "shandy"
    var typePadding = (longestLine - menuType.length) / 2
    println(" ".repeat(typePadding) + "~[$menuType]~")
    while (i < numItems) {
        if (menuTypes[i].lowercase() == menuType) {
            var lineLength = menuItems[i].length + menuPrices[i].trim().length
            var lineDiff = longestLine - lineLength
            println(menuItems[i] + ".".repeat(lineDiff + 5) + menuPrices[i])
        }
        i++
    }
    i = 0
    menuType = "elixir"
    typePadding = (longestLine - menuType.length) / 2
    println(" ".repeat(typePadding) + "~[$menuType]~")
    while (i < numItems) {
        if (menuTypes[i].lowercase() == menuType) {
            var lineLength = menuItems[i].length + menuPrices[i].trim().length
            var lineDiff = longestLine - lineLength
            println(menuItems[i] + ".".repeat(lineDiff + 5) + menuPrices[i])
        }
        i++
    }
    i = 0
    menuType = "meal"
    typePadding = (longestLine - menuType.length) / 2
    println(" ".repeat(typePadding) + "~[$menuType]~")
    while (i < numItems) {
        if (menuTypes[i].lowercase() == menuType) {
            var lineLength = menuItems[i].length + menuPrices[i].trim().length
            var lineDiff = longestLine - lineLength
            println(menuItems[i] + ".".repeat(lineDiff + 5) + menuPrices[i])
        }
        i++
    }
    i = 0
    menuType = "desert dessert"
    typePadding = (longestLine - menuType.length) / 2
    println(" ".repeat(typePadding) + "~[$menuType]~")
    while (i < numItems) {
        if (menuTypes[i].lowercase() == menuType) {
            var lineLength = menuItems[i].length + menuPrices[i].trim().length
            var lineDiff = longestLine - lineLength
            println(menuItems[i] + ".".repeat(lineDiff + 5) + menuPrices[i])
        }
        i++
    }
    i = 0
    menuType = "deserved dessert"
    typePadding = (longestLine - menuType.length) / 2
    println(" ".repeat(typePadding) + "~[$menuType]~")
    while (i < numItems - 1) {
        if (menuTypes[i].lowercase() == menuType) {
            var lineLength = menuItems[i].length + menuPrices[i].trim().length
            var lineDiff = longestLine - lineLength
            println(menuItems[i] + ".".repeat(lineDiff + 5) + menuPrices[i])
        }
        i++
    }

}