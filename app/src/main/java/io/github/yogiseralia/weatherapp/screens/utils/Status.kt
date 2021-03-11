package io.github.yogiseralia.weatherapp.screens.utils

sealed class Status {
    object SUCCESS : Status()
    object ERROR : Status()
    object LOADING : Status()
}