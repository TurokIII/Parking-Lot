package parking

class ParkingLot {
    val lot = Array<ParkingSpot>(20) { ParkingSpot() }
    var firstOpenSpot = 0


    // -> String
    // Gets action from user that should be performed
    fun readInput(input: String) {
        val command = input.split(" ")[0]

        when (command) {
            "park" -> {
                val car = createcar(input)
                this.park(car)
            }
            "leave" -> {
                leaveSpot(input)
            }
        }
    }

    // Int ->
    // Parks the given car in the first available spot
    fun park(car: Car) {
        if (this.isFull()) {
            println("Sorry, the parking lot is full.")
        } else {
            this.lot[firstOpenSpot].add(car)
            printParkJob(car)
            updateFirstOpenSpot()
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

        this.firstOpenSpot = if (first >= 0) first else 20
    }

    // String -> Car
    // Parses the user input, creates a car object, and returns it
    fun createcar(input: String): Car {
        val info = input.split(" ")
        val licensePlate = info[1]
        val carColor = info[2]
        return Car(licensePlate, carColor)
    }


    fun printParkJob(car: Car) {
        println("${car.color} car parked in spot ${++firstOpenSpot}.")
    }

    fun isFull(): Boolean {
        return this.firstOpenSpot == 20
    }

}