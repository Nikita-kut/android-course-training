package com.example.skillbox.kotlin.a7extensionsobjectsenums

interface Warrior {
    val isKilled: Boolean

    val missChance: Int

    val currentHealth: Int

    fun attack(warrior: Warrior)

    fun takeDamage(damage: Int)
}
