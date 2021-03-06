package parking

class ParkingSpot(val spotNumber: String) {
    var car: Car? = null

    fun isEmpty(): Boolean {
        return car == null
    }

    fun isNotEmpty(): Boolean {
        return car != null
    }

    fun add(car: Car) {
        this.car = car
    }

    fun clear() {
        this.car = null
    }
}