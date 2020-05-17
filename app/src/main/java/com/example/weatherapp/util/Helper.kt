package com.example.weatherapp.util

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

private val SINGLE_EXECUTOR: ExecutorService = Executors.newSingleThreadExecutor()

fun runInBackground(f: () -> Unit) {
    SINGLE_EXECUTOR.execute(f)
}