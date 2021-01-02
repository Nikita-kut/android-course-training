package com.example.skillbox.kotlin.a7extensionsobjectsenums

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
        var currentDamage: Int = 0
        if (!weapon.isAmmo) {
            weapon.recharge()
        } else {
            weapon.getAmmoForShot().forEach {
                if (hitChance.toBoolean() && !warrior.missChance.toBoolean()) {
                    currentDamage += it.getDamage2()
                }
            }
            warrior.takeDamage(currentDamage)
        }
    }

    override fun takeDamage(damage: Int) {
        currentHealth -= damage

    }

    override fun toString(): String {
        return "${this::class.simpleName}(maxHealth=$maxHealth, missChance=$missChance, hitChance=$hitChance, weapon=$weapon, currentHealth=$currentHealth)"
    }
}

class Soldier(private val pistol: AbstractWeapon = Weapons.createPistol()
) : AbstractWarrior(maxHealth = 50, missChance = 15, hitChance = 60, weapon = pistol)

class Corporal(private val automaton: AbstractWeapon = Weapons.createAutomaton()
) : AbstractWarrior(maxHealth = 75, missChance = 20, hitChance = 70, weapon = automaton)

class Captain(private val shotGun: AbstractWeapon = Weapons.createShotGun()
) : AbstractWarrior(maxHealth = 90, missChance = 25, hitChance = 80, weapon = shotGun)

class Major(private val grenadeGun: AbstractWeapon = Weapons.createGrenadeGun()
) : AbstractWarrior(maxHealth = 100, missChance = 30, hitChance = 90, weapon = grenadeGun)


