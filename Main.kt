package parking

fun main() {
    val input = readLine()!!
    val command = input.split(" ")[0]
    val lot = arrayOf(true, false)

    if (command == "park") {
        val color = input.split(" ")[2]
        println("$color car parked in spot 2.")
        lot[1] = true
    }

    if (command == "leave") {
        val spot = input.split(" ")[1].toInt()

        if (lot[spot - 1]) {
            println("Spot $spot is free.")
        } else {
            println("There is no car in spot $spot.")
        }
    }

}
