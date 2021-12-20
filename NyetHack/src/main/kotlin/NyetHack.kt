
var heroName = ""

fun main() {
//    narrate("A hero enters the town of Kronstadt.  What is their name?") { message ->
//        // Prints the message in yellow
//        "\u001B[33;1m$message\u001b[0m"
//    }
//
//    val heroName = readLine()
//    require(heroName != null && heroName.isNotEmpty()) {
//        "The hero must have a name."
//    }

    heroName = promptHeroName()

//    changeNarratorMood()
    narrate("$heroName, ${createTitle(heroName)} heads to the town square")
    visitTavern()
}

private fun promptHeroName(): String {
    narrate("A hero enters the town of Kronstadt.  What is their name?") { message ->
        // Prints the message in yellow
        "\u001B[33;1m$message\u001b[0m"
    }
        println("Madrigal")
        return "Madrigal"
}

private fun createTitle(name: String): String {
    return when {
        name.count { it.lowercase() in "aeiou" } > 4 -> "The Master of Vowels"
        name.all { it.isDigit() } -> "The Indentifiable"
        name.none { it.isLetter() } -> "The Witness Protection Member"
        else -> "The Renowned Hero"
    }
}
