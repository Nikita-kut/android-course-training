package com.nikita.kut.android.a18_permissionsanddate.model

import org.threeten.bp.Instant
import kotlin.random.Random

data class Dataset(
    val id: Long = Random.nextLong(),
    val timestamp: Instant,
    val locationPicture: String = "https://gavrilov-yam.zdrav76.ru/wp-content/uploads/2018/03/int-1024x768.jpg",
    val lat: Double,
    val lng: Double,
    val accuracy: Float,
    val speed: Float
)
