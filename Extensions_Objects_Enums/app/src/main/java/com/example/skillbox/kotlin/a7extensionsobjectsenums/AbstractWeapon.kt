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
                currentListAmmo.plus(listOf(makeAmmo()))
            }
        }
    }

    fun getAmmoForShot(needAmmo: Int): List<Ammo> {
        val ammoForShort: List<Ammo> = mutableListOf()

        for (current in 0..needAmmo) {
            if (isAmmo && currentListAmmo.size >= needAmmo) {
                when (this.fireType) {
                    FireType.SingleFire -> {
                        ammoForShort.take(1)
                        currentListAmmo.drop(1)
                    }
                    FireType.LineFire(lineSize = 5) -> {
                        ammoForShort.take(needAmmo)
                        currentListAmmo.drop(needAmmo)
                    }
                    else -> recharge()
                }
            }
        }
        return ammoForShort
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


