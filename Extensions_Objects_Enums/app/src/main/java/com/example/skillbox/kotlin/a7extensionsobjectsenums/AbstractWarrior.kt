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

class Soldier(
        val pistol: AbstractWeapon = Weapons.createPistol()
) : AbstractWarrior(maxHealth = 50, missChance = 15, hitChance = 60, weapon = pistol) {

}

class Corporal(
        val automaton: AbstractWeapon = Weapons.createAutomaton()
) : AbstractWarrior(maxHealth = 75, missChance = 20, hitChance = 70, weapon = automaton) {

}

class Captain(
        val shotGun: AbstractWeapon = Weapons.createShotGun()
) : AbstractWarrior(maxHealth = 90, missChance = 25, hitChance = 80, weapon = shotGun) {

}

class Major(
        val grenadeGun: AbstractWeapon = Weapons.createGrenadeGun()
) : AbstractWarrior(maxHealth = 100, missChance = 30, hitChance = 90, weapon = grenadeGun) {

}

