# WeatherApp
A simple Weather App built using OpenWeatherMap API that provides the required weather details at a glance.

## Description
The app displays the most relevant weather information based on the current location of the user. It displays the nearest approximate user location (City & Country), it's temperature, weather conditions, sunrise and sunset time, humidity, pressure, wind speed and rainfall.

## Architexture
The app is written entirely in Kotlin and uses the Gradle build system. 
The architecture is built around [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/).
The logic is kept away from Activities and Fragments and moved it to [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)s following the MVVM Architecture and inline with the best practice. The data is observed using [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) and update changes in the UI.

It uses the latest Jetpack Components.
- [Retrofit](https://square.github.io/retrofit/): A type-safe HTTP Client for Android and Java.
- [Dagger2](https://github.com/google/dagger): Compile-time framework for Dependency Injection.

### Retrofit
The app uses Retrofit to streamline interaction with the OpenWeatherMap API. It is used to retrieve the JSON response and makes use of Moshi converter to parse the JSON response to Java objects and vice versa.

### Dagger
The app uses Dagger to remove the biolerplate code and handles dependencies between Actvities, Fragments and classes. All activities and classes use Dagger to eliminate manual dependency injection.

## Features
- Syncs API data with locally in Room Database to save battery and reduce API calls.
- Implements Kotlin Coroutines to manage background tasks effectively and reduce the need for callbacks while dealing with APIs.

## Screenshots
The background changes based on the time of day.
- Morning
- Twilight
- Night

<p align="center">
  <img src="https://user-images.githubusercontent.com/39412016/82552678-35442900-9b80-11ea-80f8-5b417c2f0a1c.png" width="270" height="480">
  <img src="https://user-images.githubusercontent.com/39412016/82552956-c74c3180-9b80-11ea-95de-5a5ecbf55c7a.png" width="270" height="480">
  <img src="https://user-images.githubusercontent.com/39412016/82552977-cfa46c80-9b80-11ea-8f77-8e5a66284ec1.png" width="270" height="480">
</p>

## Under Development
- Weather Information of User specified location, to make it more useful.
