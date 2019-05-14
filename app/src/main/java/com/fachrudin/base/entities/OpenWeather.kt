package com.fachrudin.base.entities

/**
 * @author achmad.fachrudin
 * @date 14-May-19
 */
data class OpenWeather (
    val name: String,
    val main: Main,
    val weather: List<Weather>
) {
    data class Main (val temp: String)
}