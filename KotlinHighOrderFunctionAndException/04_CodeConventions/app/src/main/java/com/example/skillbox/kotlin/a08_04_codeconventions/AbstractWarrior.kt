package com.example.skillbox.kotlin.a7extensionsobjectsenums

import com.example.skillbox.kotlin.a08_03_exception.NoAmmoException
import java.lang.IndexOutOfBoundsException

abstract class AbstractWarrior(
        val maxHealth: Int,
        override val missChance: Int,
        val hitChance: Int,
        val weapon: AbstractWeapon
) : Warrior {

    override var currentHealth: Int = maxHealth

    override val isKilled: Boolean
        get() {
            return currentHealth <= 0
        }

    override fun attack(warrior: Warrior) {
        var currentDamage = 0
        try {
            weapon.getAmmoForShot().forEach {
                if (hitChance.toBoolean() && !warrior.missChance.toBoolean()) {
                    currentDamage += it.getDamage2()
                }
                warrior.takeDamage(currentDamage)
            }
        } catch (e: NoAmmoException) {
            weapon.recharge()
            println("Weapon recharge")
        }
    }

    override fun takeDamage(damage: Int) {
        currentHealth -= damage

    }

    override fun toString(): String {
        return "${this::class.simpleName}(maxHealth=$maxHealth, missChance=$missChance, hitChance=$hitChance, weapon=$weapon, currentHealth=$currentHealth)"
    }
}

class Soldier(pistol: AbstractWeapon = Weapons.createPistol()
) : AbstractWarrior(maxHealth = 50, missChance = 15, hitChance = 60, weapon = pistol)

class Corporal(automaton: AbstractWeapon = Weapons.createAutomaton()
) : AbstractWarrior(maxHealth = 75, missChance = 20, hitChance = 70, weapon = automaton)

class Captain(shotGun: AbstractWeapon = Weapons.createShotGun()
) : AbstractWarrior(maxHealth = 90, missChance = 25, hitChance = 80, weapon = shotGun)

class Major(grenadeGun: AbstractWeapon = Weapons.createGrenadeGun()
) : AbstractWarrior(maxHealth = 100, missChance = 30, hitChance = 90, weapon = grenadeGun)


