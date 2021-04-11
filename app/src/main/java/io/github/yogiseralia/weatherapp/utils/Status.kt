package io.github.yogiseralia.weatherapp.utils

sealed class Status {
    object SUCCESS : Status()
    object ERROR : Status()
    object LOADING : Status()
}