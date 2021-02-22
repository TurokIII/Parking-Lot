package parking

fun main() {
    val parkingLot: ParkingLot

    val startInput = getStartupDetails()
    if (startInput == "exit") return else parkingLot = createParkingLot(startInput)

    getUserInput(parkingLot)

}

fun getStartupDetails(): String {
    val createLotRegex = Regex("""create \d+""")

    while (true) {
        val input = readLine()!!

        // terminate program if exit was input
        if (input == "exit") return "exit"

        if (input.matches(createLotRegex)) {
            return input
        } else {
            println("Sorry, a parking lot has not been created.")
        }
    }
}

fun createParkingLot(input: String): ParkingLot {
    val lotSize = input.split(" ")[1].toInt()
    println("Created a parking lot with $lotSize spots.")

    return ParkingLot(lotSize)
}

fun getUserInput(parkingLot: ParkingLot) {
    while (true) {
        val input = readLine()!!
        val command = input.split(" ")[0]

        when (command) {
            "exit" -> return
            "park" -> parkingLot.park(input)
            "leave" -> parkingLot.leaveSpot(input)
            "status" -> parkingLot.printStatus()
            "create" -> {
                val size = input.split(" ")[1].toInt()
                parkingLot.resize(size)
            }
        }
    }
}



