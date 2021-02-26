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
            "park" -> parkCarInLot(parkingLot, input)
            "leave" -> leaveParkingLot(parkingLot, input)
            "status" -> parkingLot.printStatus()
            "create" -> resizeParkingLot(parkingLot, input)
            "reg_by_color" -> printLicenseByColor(parkingLot, input)
            "spot_by_color" -> printSpotNumberByColor(parkingLot, input)
            "spot_by_reg" -> printSpotNumbersByLicensePlate(parkingLot, input)
        }
    }
}

/* ParkingLot, String ->
 * Parses license plate info and color from user input, and then calls the park() method of Parking Lot
 * to park the car
 */
fun parkCarInLot(parkingLot: ParkingLot, input: String) {
    val licensePlate = input.split(" ")[1]
    val color = input.split(" ")[2]
    parkingLot.park(licensePlate, color)
}


/* ParkingLot, String ->
 * Parses the parking lot spot number from the user input, and then removes the car from the spot
 * by calling the leaveSpot method of ParkingLot
 */
fun leaveParkingLot(parkingLot: ParkingLot, input: String) {
    val spot = input.split(" ")[1].toInt() - 1
    parkingLot.leaveSpot(spot)
}

/* ParkingLot, String ->
 * Parses the new size from the userInput, and then calls the resize method of ParkingLot
 * to resize (create a new lot of given size)
 */

fun resizeParkingLot(parkingLot: ParkingLot, input: String) {
    val size = input.split(" ")[1].toInt()
    parkingLot.resize(size)
}

/* ParkingLot, String ->
 * Parses the car color from user input and then calls the printLicenseByColor method of ParkingLot
 * to print the license plate of all the cars of that color
 */
fun printLicenseByColor(parkingLot: ParkingLot, input: String) {
    val color = input.split(" ")[1]
    parkingLot.printLicenseByColor(color)
}

/* ParkingLot, String ->
 * Parses the car color from user input and then calls the printSpotNumberByColor method of ParkingLot
 *  to print the spot number of all the cars of that color
 */
fun printSpotNumberByColor(parkingLot: ParkingLot, input: String) {
    val color = input.split(" ")[1]
    parkingLot.printSpotNumberByColor(color)
}

/* ParkingLot, String ->
 * Parses the car license plate from user input and then calls the printSpotNumberByColor
 * method of ParkingLot to print the spot number of all the cars with that license plate
 */
fun printSpotNumbersByLicensePlate(parkingLot: ParkingLot, input: String) {
    val licensePlate = input.split(" ")[1]
    parkingLot.printSpotNumberByLicensePlate(licensePlate)
}





