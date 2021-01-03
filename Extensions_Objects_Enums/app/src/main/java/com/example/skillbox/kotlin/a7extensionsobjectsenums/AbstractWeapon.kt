package com.example.skillbox.kotlin.a7extensionsobjectsenums

abstract class AbstractWeapon(
        val maxAmmo: Int,
        val fireType: FireType
) {
    private var currentListAmmo: List<Ammo> = mutableListOf()

    val isAmmo: Boolean
        get() {
            return currentListAmmo.isNotEmpty()
        }

    abstract fun makeAmmo(): Ammo

    fun recharge() {
        currentListAmmo = MutableList(size = maxAmmo) { makeAmmo() }
    }

    fun getAmmoForShot(): List<Ammo> {
        val ammoForShot = mutableListOf<Ammo>()

        if (currentListAmmo.size <= fireType.lineSize) {
            recharge()
        } else {
            for (current in 0 until fireType.lineSize) {
                ammoForShot.add((currentListAmmo as MutableList<Ammo>).removeAt(0))
            }
        }
        return ammoForShot
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AbstractWeapon

        if (maxAmmo != other.maxAmmo) return false
        if (fireType != other.fireType) return false
        if (currentListAmmo != other.currentListAmmo) return false

        return true
    }

    override fun hashCode(): Int {
        var result = maxAmmo
        result = 31 * result + fireType.hashCode()
        result = 31 * result + currentListAmmo.hashCode()
        return result
    }

    override fun toString(): String {
        return "AbstractWeapon(maxAmmo=$maxAmmo, fireType=$fireType)"
    }
}

object Weapons {
    fun createPistol(): AbstractWeapon {
        return object : AbstractWeapon(15, FireType.SingleFire) {
            override fun makeAmmo(): Ammo {
                return Ammo.PISTOLPATRON
            }
        }
    }

    fun createShotGun(): AbstractWeapon {
        return object : AbstractWeapon(5, FireType.SingleFire) {
            override fun makeAmmo(): Ammo {
                return Ammo.SHOTGUNPATRON
            }
        }
    }

    fun createAutomaton(): AbstractWeapon {
        return object : AbstractWeapon(30, FireType.LineFire(5)) {
            override fun makeAmmo(): Ammo {
                return Ammo.AUTOMATONPATRON
            }
        }
    }

    fun createGrenadeGun(): AbstractWeapon {
        return object : AbstractWeapon(3, FireType.SingleFire) {
            override fun makeAmmo(): Ammo {
                return Ammo.GRENADE
            }
        }
    }
}


