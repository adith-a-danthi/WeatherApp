package com.example.weatherapp.data.source.remote.api.response.inner

import com.squareup.moshi.Json

/*

"wind": {
    "speed": 0.47,
    "deg": 107.538
},

*/

data class Wind(
    val speed : Float,
    @field:Json(name = "deg") val degree: Float
)