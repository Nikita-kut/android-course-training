package com.example.skillbox.kotlin.a7extensionsobjectsenums

import kotlin.random.Random
import kotlin.random.nextInt

enum class Ammo(
        val damage: Int,
        private val chanceOfCriticalDamage: Int,
        private val coefOfCriticalDamage: Int
) {
    PISTOLPATRON(damage = 5, chanceOfCriticalDamage = 15, coefOfCriticalDamage = 2),
    AUTOMATONPATRON(damage = 7, chanceOfCriticalDamage = 10, coefOfCriticalDamage = 3),
    SHOTGUNPATRON(damage = 10, chanceOfCriticalDamage = 15, coefOfCriticalDamage = 4),
    GRENADE(damage = 10, chanceOfCriticalDamage = 5, coefOfCriticalDamage = 5);

    fun getDamage(ammo: Ammo): Int {
        return if (chanceOfCriticalDamage.toBoolean()) damage * coefOfCriticalDamage else damage
    }
}
fun Int.toBoolean(): Boolean {
    return Random.nextInt(0..100) <= this
}