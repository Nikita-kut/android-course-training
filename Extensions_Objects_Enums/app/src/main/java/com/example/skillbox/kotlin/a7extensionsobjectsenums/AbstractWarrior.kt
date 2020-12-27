package com.example.skillbox.kotlin.a7extensionsobjectsenums

import kotlin.random.Random
import kotlin.random.nextInt

abstract class AbstractWarrior(
        val maxHealth: Int,
        override val missChance: Int,
        val hitChance: Int,
        val weapon: AbstractWeapon
) : Warrior {

    private fun Int.toBoolean(): Boolean {
        return Random.nextInt(0..100) <= this
    }

    val currentHealth: Int = maxHealth

    override val isKilled: Boolean
        get() = TODO("Not yet implemented")

    override fun attack(warrior: Warrior) {
        for (current in 1..10)
            if (!weapon.isAmmo) {
                weapon.recharge()
                break
            } else {
                weapon.getAmmoForShot().forEach {
                    if (hitChance.toBoolean() >= missChance.toBoolean()) {
                        it.getDamage(it)
                    } else println("missHit")
                }
            }
    }

    override fun takeDamage(damage: Int) {
        currentHealth - damage

    }
}