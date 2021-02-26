package parking

class ParkingLot(var size: Int) {
    var lot = Array<ParkingSpot>(size) { ParkingSpot((it + 1).toString()) }
    var firstOpenSpot = 0
    var parkedCars = 0


    // String, String ->
    // Creates a Car from the given license plate and color, and parks it in first available spot
    fun park(licensePlate: String, color: String) {
        val car = createcar(licensePlate, color)

        if (this.isFull()) {
            println("Sorry, the parking lot is full.")
        } else {
            this.lot[firstOpenSpot].add(car)
            printParkJob(car)
            updateFirstOpenSpot()
            this.parkedCars++
        }
    }

    // Int ->
    // Removes the car from the given spot
    fun leaveSpot(spot: Int) {

        if (this.lot[spot].isEmpty()) {
            println("No car is parked at spot #$spot!")
        } else {
            this.lot[spot].clear()
            println("Spot ${spot + 1} is free.")
        }

        if (spot < this.firstOpenSpot) this.firstOpenSpot = spot
        parkedCars--
    }

    // Searches for first open spot in lot, and updates firstOpenSpot
    fun updateFirstOpenSpot() {
        var first = -1
        for (i in this.lot.indices) {
            if (this.lot[i].isEmpty()) {
                first = i
                break
            }
        }

        this.firstOpenSpot = if (first >= 0) first else this.size
    }

    // Re assigns lot to a new size, resets all the member variables
    fun resize(newSize: Int) {
        this.lot = Array<ParkingSpot>(newSize) { ParkingSpot((it + 1).toString()) }
        this.parkedCars = 0
        this.firstOpenSpot = 0
        this.size = newSize
        println("Created a parking lot with $size spots.")
    }

    // String, String -> Car
    // Creates a car from the given license plate and color
    fun createcar(licensePlate: String, color: String): Car {
        val upperCaseColor = color.toUpperCase()
        val upperLicensePlate = licensePlate.toUpperCase()
        return Car(upperLicensePlate, upperCaseColor)
    }

    // -> Boolean
    // Returns true if parking lot is full, other wise false
    fun isFull(): Boolean {
        return this.firstOpenSpot == this.size
    }

    // Car ->
    // Prints the color of the car and what spot it parked in
    fun printParkJob(car: Car) {
        println("${car.color} car parked in spot ${++firstOpenSpot}.")
    }

    // Prints the spot number, license plate, and color of each car in the lot
    // If lot is empty, prints false
    fun printStatus() {
        if (this.parkedCars > 0) {
            for (i in this.lot.indices) {
                val car = this.lot[i].car
                val spotNumber = i + 1

                if (car != null) {
                    println("$spotNumber ${car.licensePlate} ${car.color}")
                }
            }
        } else {
            println("Parking lot is empty.")
        }
    }

    // String -> MutableList<String>
    // Finds all the cars of the given color, adds their license plates to a MutableList, and returns it
    fun findLicenseByColor(color: String): MutableList<String> {
        val licensePlates = mutableListOf<String>()

        for (parkingSpot in this.lot) {
            if (parkingSpot.car?.color == color) {
                licensePlates.add(parkingSpot.car!!.licensePlate)
            }
        }

        return licensePlates
    }

    /* String ->
     * Calls findLicenseByColor to get a MutableList of all the license plates of cars of the given color
     * and then passes that MutableList to the printInfo method to print all of their license plates
     */
    fun printLicenseByColor(color: String) {
        val upperColor = color.toUpperCase()
        val cars = findLicenseByColor(upperColor)

        if (cars.isEmpty()) {
            println("No cars with color $color were found.")
        } else {
            printInfo(cars)
        }
    }

    /* String -> MutableList<String>
     * Finds all the cars of the given color, adds the spotNumber of the ParkingSpot containing them to
     * a MutableList and then returns it
     */
    fun findSpotNumberByColor(color: String): MutableList<String> {
        val spots = mutableListOf<String>()

        for (parkingSpot in this.lot) {
            if (parkingSpot.car?.color == color) {
                spots.add(parkingSpot.spotNumber)
            }
        }

        return spots
    }

    /* String ->
    * Calls findSpotNumberByColor to get a MutableList of all the spotNumbers of
    * ParkingSpots containing a car of that color, and then passes that MutableList
    * to the printInfo method to print the spotNumbers
    */
    fun printSpotNumberByColor(color: String) {
        val upperColor = color.toUpperCase()
        val spotNumbers = findSpotNumberByColor(upperColor)

        if (spotNumbers.isEmpty()) {
            println("No cars with color $color were found.")
        } else {
            printInfo(spotNumbers)
        }
    }

    /* String -> MutableList<String>
     * Finds all the cars of the given color, adds the spotNumber of the ParkingSpot containing them to
     * a MutableList and then returns it
     */
    fun findSpotNumberByLicensePlate(licensePlate: String): MutableList<String> {
        val spots = mutableListOf<String>()

        for (parkingSpot in this.lot) {
            if (parkingSpot.car?.licensePlate == licensePlate) {
                spots.add(parkingSpot.spotNumber)
            }
        }

        return spots
    }

    /* String ->
     * Calls findSpotNumberByLicensePlate to get a MutableList of all the spotNumbers of
     * ParkingSpots containing a car with that licensePlate, and then passes that MutableList
     * to the printInfo method to print the spotNumbers
     */
    fun printSpotNumberByLicensePlate(licensePlate: String) {
        val upperLicensePlate = licensePlate.toUpperCase()
        val spotNumbers = findSpotNumberByLicensePlate(upperLicensePlate)

        if (spotNumbers.isEmpty()) {
            println("No cars with registration number $licensePlate were found.")
        } else {
            printInfo(spotNumbers)
        }
    }

    // String ->
    // Joins all the elements in the given List with a comma, and prints resulting string
    fun printInfo(elements: List<String>) {
        val output = elements.joinToString(", ")
        println(output)
    }

}