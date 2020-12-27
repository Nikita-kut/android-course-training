package com.example.skillbox.kotlin.a7extensionsobjectsenums

interface Warrior {
    val isKilled: Boolean

    val missChance: Int

    fun attack (warrior: Warrior)

    fun takeDamage (damage: Int)
}