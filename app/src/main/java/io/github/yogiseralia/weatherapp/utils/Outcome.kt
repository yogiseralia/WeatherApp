package io.github.yogiseralia.weatherapp.utils

data class Outcome<out T>(
    val status: Status, val data: T, val message: String
) {
    companion object {
        fun <T> success(data: T): Outcome<T> =
            Outcome(status = Status.SUCCESS, data = data, message = "")

        fun <T> error(data: T?, message: String): Outcome<T?> =
            Outcome(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T?): Outcome<T?> = Outcome(Status.LOADING, data, "")
    }
}