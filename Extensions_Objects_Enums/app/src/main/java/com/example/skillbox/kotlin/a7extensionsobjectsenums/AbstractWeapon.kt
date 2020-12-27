package com.example.skillbox.kotlin.a7extensionsobjectsenums

abstract class AbstractWeapon(
        val maxAmmo: Int,
        val fireType: FireType
) {
    var currentListAmmo: List<Ammo> = mutableListOf()

    val isAmmo: Boolean
        get() {
            return currentListAmmo.isNotEmpty()
        }

    abstract fun makeAmmo(): Ammo

    fun recharge() {
        if (!isAmmo) {
            for (current in 0..maxAmmo) {
                if (currentListAmmo.size > maxAmmo) {
                    currentListAmmo.size == maxAmmo
                    break
                } else {
                    currentListAmmo.plus(listOf(makeAmmo()))
                }
            }
        }
    }

    fun getAmmoForShot(needAmmo: Int): List<Ammo> {
        val ammoForShot: List<Ammo> = mutableListOf()

        for (current in 0..needAmmo) {
            if (isAmmo && currentListAmmo.size <= maxAmmo) {
                when (this.fireType) {
                    FireType.SingleFire -> {
                        ammoForShot.take(1)
                        currentListAmmo.drop(1)
                    }
                    FireType.LineFire(lineSize = 5) -> {
                        ammoForShot.take(5)
                        currentListAmmo.drop(5)
                    }
                    else -> recharge()
                }
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


