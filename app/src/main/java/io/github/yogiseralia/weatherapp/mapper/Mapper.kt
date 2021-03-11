package io.github.yogiseralia.weatherapp.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}