package parking

class ParkingLot(var size: Int) {
    var lot = Array<ParkingSpot>(size) { ParkingSpot() }
    var firstOpenSpot = 0
    var parkedCars = 0


    // Int ->
    // Parks the given car in the first available spot
    fun park(input: String) {
        val car = createcar(input)

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
    fun leaveSpot(input: String) {
        val spot = input.split(" ")[1].toInt() - 1

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
        this.lot = Array<ParkingSpot>(newSize) { ParkingSpot() }
        this.parkedCars = 0
        this.firstOpenSpot = 0
        this.size = newSize
        println("Created a parking lot with $size spots.")
    }

    // String -> Car
    // Parses the user input, creates a car object, and returns it
    fun createcar(input: String): Car {
        val info = input.split(" ")
        val licensePlate = info[1]
        val carColor = info[2]
        return Car(licensePlate, carColor)
    }

    // -> Boolean
    // Returns true if parking lot is full, other wise false
    fun isFull(): Boolean {
        return this.firstOpenSpot == this.size
    }

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

}