package io.github.yogiseralia.weatherapp.utils.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}