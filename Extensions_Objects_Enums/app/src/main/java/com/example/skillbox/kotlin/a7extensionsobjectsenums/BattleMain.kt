package com.example.skillbox.kotlin.a7extensionsobjectsenums

fun main() {
    val pistol: AbstractWeapon = Weapons.createPistol()

    pistol.getAmmoForShot()
}